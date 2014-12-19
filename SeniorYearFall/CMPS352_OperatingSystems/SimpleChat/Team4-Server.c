/*
 * Program:    Team Assignment 2 (Assignment 6)
 * Authors:    Adam Miller
 *             Eamonn Orr
 *             Ronnie Passaro
 * Date:       November 7, 2014
 * File Name:  Team4-Server.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <netdb.h>
#include <pthread.h>

// you must uncomment the next two lines and make changes
#define HOST "server1.cs.scranton.edu" // the hostname of the HTTP server
#define PORT "32400"                   // the HTTP port client will be connecting to
#define BACKLOG 10                     // how many pending connections queue will hold
#define BUFFERSIZE 128

//Initialize the linked list structure
struct userInfo
{
    char userName[BUFFERSIZE+1];
    char firstName[BUFFERSIZE+1];
    char lastName[BUFFERSIZE+1];
    char password[BUFFERSIZE+1];
    int socket;
    struct userInfo* next;
};

// get internet address
void *get_in_addr(struct sockaddr * sa);

// get a server socket
int get_server_socket(char *hostname, char *port);

// start server's listening
int start_server(int serv_socket, int backlog);

// accept a connection from client
int accept_client(int serv_sock);

// start subserver as a thread
void start_subserver(int client_sock);

// subserver -> subserver
void *subserver(void* clients);

void startChat(struct userInfo* user);

int receiveAndSendMessage(int recv_sock, int send_sock);

int loginOrRegister(int client_sock);

int registerUser(int client_sock, struct userInfo *newInfo);

// adds a user to the end of the registered list
void appendUser(struct userInfo* newInfo);

int login(int client_sock, struct userInfo* temp);

void copyUserInfo(struct userInfo *dest, struct userInfo *source);

int checkifLoggedin(struct userInfo *newUser);

int uniqueUser(char userName[BUFFERSIZE+1]);

void chatCommand(char message[BUFFERSIZE], struct userInfo* chat);

struct userInfo* isOnline(char* userName);

void removeUser(char user[BUFFERSIZE]);

void inviteChat(char message[BUFFERSIZE], struct userInfo* chat);

void acceptChat(char message[BUFFERSIZE], struct userInfo* chat);

void declineChat(char message[BUFFERSIZE], struct userInfo* chat);

void exitChat(char message[BUFFERSIZE], struct userInfo* chat);

void quitChat(char message[BUFFERSIZE], struct userInfo* chat);

/*** Race Conditions
 socket connecting - solved
 adding and removing logged in users from the list
 registering(adding) users to the main file
*/

pthread_mutex_t list_lock;
pthread_mutex_t sock_lock;
pthread_mutex_t reg_lock;

struct userInfo *list;
int client_sock;

int main(void)
{
    list = NULL;
    pthread_mutex_init(&list_lock, 0);
    pthread_mutex_init(&sock_lock, 0);
    
    int serv_sock_fd;			// http server socket
    
    // steps 1-2: get a socket and bind to ip address and port
    serv_sock_fd = get_server_socket(HOST, PORT);
    
    // step 3: get ready to accept connections
    if (start_server(serv_sock_fd, BACKLOG) == -1) {
        printf("start server error\n");
        exit(1);
    }
    
    while(1)
    { // accept() client connections
        
        // step 4: accept two client connections
        pthread_mutex_lock(&sock_lock);
        if ((client_sock = accept_client(serv_sock_fd)) == -1) {
            continue;
        }
        
        start_subserver(client_sock);
    }
    return 0;
}

int get_server_socket(char *hostname, char *port) {
    struct addrinfo hints, *servinfo, *p;
    int status;
    int server_socket = 0;
    int yes = 1;
    
    memset(&hints, 0, sizeof hints);
    hints.ai_family = PF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_flags = AI_PASSIVE;
    
    if ((status = getaddrinfo(hostname, port, &hints, &servinfo)) != 0) {
        printf("getaddrinfo: %s\n", gai_strerror(status));
        exit(1);
    }
    
    for (p = servinfo; p != NULL; p = p ->ai_next) {
        // step 1: create a socket
        if ((server_socket = socket(p->ai_family, p->ai_socktype,
                                    p->ai_protocol)) == -1)
        {
            printf("socket socket \n");
            continue;
        }
        // if the port is not released yet, reuse it.
        if (setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int)) == -1) {
            printf("socket option\n");
            continue;
        }
        
        // step 2: bind socket to an IP addr and port
        if (bind(server_socket, p->ai_addr, p->ai_addrlen) == -1) {
            printf("socket bind \n");
            continue;
        }
        break;
    }
    freeaddrinfo(servinfo);   // servinfo structure is no longer needed. free it.
    
    return server_socket;
}

int start_server(int serv_socket, int backlog)
{
    int status = 0;
    if ((status = listen(serv_socket, backlog)) == -1) {
        printf("socket listen error\n");
    }
    return status;
}

int accept_client(int serv_sock)
{
    int reply_sock_fd = -1;
    socklen_t sin_size = sizeof(struct sockaddr_storage);
    struct sockaddr_storage client_addr;
    char client_printable_addr[INET6_ADDRSTRLEN];
    
    // accept a connection request from a client
    // the returned file descriptor from accept will be used
    // to communicate with this client.
    if ((reply_sock_fd = accept(serv_sock,
                                (struct sockaddr *)&client_addr, &sin_size)) == -1) {
        printf("socket accept error\n");
    }
    else {
        // here is for info only, not really needed.
        inet_ntop(client_addr.ss_family, get_in_addr((struct sockaddr *)&client_addr),
                  client_printable_addr, sizeof client_printable_addr);
        printf("server: connection from %s at port %d\n", client_printable_addr,
               ((struct sockaddr_in*)&client_addr)->sin_port);
    }
    
    return reply_sock_fd;
}

/* NOTE: the value of reply_sock_fd is passed to pthread_create() as the
 parameter, instead of the address of reply_sock_fd. This is
 necessary because reply_sock_fd is a parameter (or local varaible)
 of start_server. After start_server returns, reply_sock_id becomes
 invalid. if the new thread is not executed before start_server()
 returns, the thread would be an invalid reply_sock_fd. Passing the
 value of reply_sock_fd avoids this problem, since the valid is
 copied to the local variable of the thread. Because our server1 is
 of 64-bits and int in c is 32 bits, we need to do type conversions
 to not cause compilation warnings
 */
void start_subserver(int client_sock)
{
    
    pthread_t pthread;
    long client_sock_long = client_sock;
    pthread_mutex_unlock(&sock_lock);
    
    if (pthread_create(&pthread, NULL, subserver, (void *)client_sock_long) != 0) {
        printf("failed to start subserver\n");
    }
    else {
        printf("Chat session %d started\n", pthread);
    }
}

/* this is the subserver who really communicate
 with client through the client1_sock and client2_sock   */
void *subserver(void* client_socket)
{
    struct userInfo user = *(struct userInfo*) malloc(sizeof(struct userInfo));
    //Gets the sockets of the clients from the server
    user.socket = (int) client_sock;
    user.next = NULL;
    
    int check = 1;
    while (check)
    {
        if (loginOrRegister(user.socket))
        {
            check = registerUser(user.socket, &user);
        }
        else
        {
            check = login(user.socket, &user);
        }
    }
    
    printf("subserver ID = %d\n", pthread_self());
    startChat(&user);
    
    return 0;
}

void startChat(struct userInfo* user)
{
    struct userInfo* chat = NULL;
    struct userInfo temp = *(struct userInfo*) malloc(sizeof(struct userInfo));
    chat = &temp;
    copyUserInfo(chat, user);
    chat->next = NULL;
    
    char name[BUFFERSIZE*2];
    strcpy(name, chat->firstName);
    strcat(name, " ");
    strcat(name, chat->lastName);

    send(chat->socket, name, sizeof(name), 0);
    
    int messageRcv = (BUFFERSIZE+1);
    int messageSnd = (BUFFERSIZE+1);
    
    char snd[messageSnd];
    char rcv[messageRcv];

    int messLen = -1;
    
    int check = 1;
    while (chat->socket)
    {
        messLen = recv(chat->socket, rcv, sizeof(rcv), 0);
        rcv[messLen-1] = '\0';
        
        printf("received: %s\n", rcv);

        if(rcv[0] == '/')
        {
            chatCommand(rcv, chat);
        }
        else if(chat->next != NULL)
        {
            strcpy(snd, name);
            strcat(snd, ": ");
            strcat(snd, rcv);
            chat->next->socket = chat->next->socket;
            if(send(chat->next->socket, snd, strlen(snd), 0) <= 0)
            {
                printf("You done goofed\n");
            }
            printf("sock: %d\n", chat->next->socket);
            printf("name: %s\n", chat->next->userName);
            printf("sent: ");
            printf("%s\n", snd);

        }
    }
}

void chatCommand(char message[BUFFERSIZE], struct userInfo* chat)
{
    char inv[7] =  "/invite";
    char ext[5] =  "/exit";
    char quit[5] = "/quit";
    char no[3] = "/no";
    char yes[4] = "/yes";
    char yup[4] = "/yup";
    
    if (strncasecmp(inv, message, 7) == 0)
    {
        inviteChat(message, chat);
    }
    else if (strncasecmp(no, message, 3) == 0)
    {
        declineChat(message, chat);
    }
    else if (strncasecmp(yes, message, 4) == 0)
    {
        acceptChat(message, chat);
    }
    else if (strncasecmp(yup, message, 4) == 0)
    {
        //Tells the sender of the invite the person has joined
        char* mess = strchr(message, ' ') + 1;
        struct userInfo *user = isOnline(mess);
        
        if(user != NULL)
        {
            if(chat->next != NULL)
            {
                char ex[BUFFERSIZE] = "/ext ";
                strcat(ex, chat->next->userName);
                send(chat->next->socket, ex, strlen(ex), 0);
                
                chat->next = NULL;
                char* mess = "You have left the chat";
                printf("mess: %s", mess);
                send(chat->socket, mess, strlen(mess), 0);
            }
            
            struct userInfo *new = (struct userInfo*) malloc(sizeof(struct userInfo*));
            memcpy(new, user, sizeof(struct userInfo));
            new->socket = user->socket;
            new->next = NULL;
            
            struct userInfo* temp = chat;
            chat->next = new;
            temp = chat->next;
            temp = NULL;
        }

    }
    else if(strncasecmp(ext, message, 5) == 0)
    {
        //Tells a user that someone has exited the chat
        if(chat->next != NULL)
        {
            char ex[BUFFERSIZE] = "/ext ";
            strcat(ex, chat->next->userName);
            send(chat->next->socket, ex, strlen(ex), 0);
            
            chat->next = NULL;
            char* mess = "You have left the chat";
            printf("mess: %s", mess);
            send(chat->socket, mess, strlen(mess), 0);

        }
    }
    else if (strncasecmp("/ext", message, 4) == 0)
    {
        exitChat(message, chat);
    }
    else if (strncasecmp(quit, message, 5) == 0)
    {
        quitChat(message, chat);
    }
}

void inviteChat(char message[BUFFERSIZE], struct userInfo* chat)
{
    //Gets the username from the string then checks to see if they are online
    char* temp = strchr(message, ' ') + 1;
    struct userInfo* user = isOnline(temp);
    
    if (user == NULL)
    {
        char* mess = "User is not online";
        send(chat->socket, mess, BUFFERSIZE, 0);
    }
    else
    {
        char name[BUFFERSIZE] = "/";
        strcat(name, chat->userName);
        strcat(name, " has asked you to join their chat (/yes or /no)\n");
        printf("name: %s\n", name);
        send(user->socket, name, sizeof(name), 0);
        send(user->socket, chat->userName, BUFFERSIZE, 0);
    }

}


void declineChat(char message[BUFFERSIZE], struct userInfo* chat)
{
    //Gets the userInfo of the user who sent the request in order to decline the invite
    char* mess = strchr(message, ' ') + 1;
    struct userInfo *user = isOnline(mess);
    
    char name[BUFFERSIZE] = "";
    strcat(name, chat->firstName);
    strcat(name, " ");
    strcat(name, chat->lastName);
    

    
    if (user == NULL)
    {
        char temp[BUFFERSIZE] = "Inviter has logged off\n";
        send(chat->socket, temp, sizeof(temp), 0);
    }
    else
    {
        strcat(name, " has declined the invite\n");
        send(user->socket, name, sizeof(name), 0);
    }
    
}


void acceptChat(char message[BUFFERSIZE], struct userInfo* chat)
{
    if(chat->next != NULL)
    {
        char ex[BUFFERSIZE] = "/ext ";
        strcat(ex, chat->next->userName);
        send(chat->next->socket, ex, strlen(ex), 0);
    
        chat->next = NULL;
        char* mess = "You have left the chat";
        printf("mess: %s", mess);
        send(chat->socket, mess, strlen(mess), 0);
    }
    
    printf("accept\n");
    
    //Gets the userInfo of the user who sent the request in order to decline the invite
    char* mess = strchr(message, ' ') + 1;
    struct userInfo *user = isOnline(mess);
    
    char name[BUFFERSIZE] = "//";
    strcat(name, chat->firstName);
    strcat(name, " ");
    strcat(name, chat->lastName);
    
    if (user == NULL)
    {
        char temp[BUFFERSIZE] = "Inviter has logged off\n";
        send(chat->socket, temp, sizeof(temp), 0);
    }
    else
    {
        strcat(name, " has joined the chat\n");
        send(user->socket, name, sizeof(name), 0);
        send(user->socket, chat->userName, sizeof(chat->userName), 0);
        
        struct userInfo *new = (struct userInfo*) malloc(sizeof(struct userInfo*));
        memcpy(new, user, sizeof(struct userInfo));
        new->socket = user->socket;
        new->next = NULL;

        struct userInfo* temp = chat;
        
        chat->next = new;
        temp = temp->next;
        temp = NULL;
    }
}


void exitChat(char message[BUFFERSIZE], struct userInfo* chat)
{
    char temp[BUFFERSIZE] = "";
    strcat(temp, chat->next->userName);
    strcat(temp, " has left the chat");
    send(chat->socket, temp, strlen(temp), 0);
    //Sets the chat->next to null which clears out anyone in their chat room
    chat->next = NULL;
    
}

void quitChat(char message[BUFFERSIZE], struct userInfo* chat)
{
    //Sends a message to the other users telling them <current> user has left the chat
    send(chat->socket, message, strlen(message), 0);
    if(chat->next != NULL)
    {
        char ex[BUFFERSIZE] = "/ext ";
        strcat(ex, chat->next->userName);
        send(chat->next->socket, ex, strlen(ex), 0);
    }
    //Closes the users sockets and removes them from the list containing all online users
    struct userInfo *temp = isOnline(chat->userName);
    close(temp->socket);
    removeUser(temp->userName);
    close(chat->socket);
    pthread_exit(&pthread_self);
}

//Iterates through all online users to check to see if someones on
struct userInfo* isOnline(char* userName)
{
    struct userInfo* temp = list;
    
    while (temp != NULL)
    {
        if(strcmp(userName, temp->userName) == 0)
        {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

int receiveAndSendMessage(int recv_sock, int send_sock) {
    char buffer[BUFFERSIZE+1];
    char ex[4] = "EXIT";
    
    int read_count = -1;
    read_count = recv(recv_sock, buffer, BUFFERSIZE, 0);
    buffer[read_count] = '\0'; // to safe-guard the string
    
    //Sends it to the second client if client 2 hasn't left
    send(send_sock, buffer, BUFFERSIZE, 0);
    
    if (buffer[0] == ex[0] && buffer[1] == ex[1] && buffer[2] == ex[2] && buffer[3] == ex[3])
        return 0;
    return 2;
}

int loginOrRegister(int client_sock) {
    int32_t resp;
    recv(client_sock, &resp, sizeof(int32_t), 0);
    int32_t res = ntohl(resp);
    
    return res;
}

int registerUser(int client_sock, struct userInfo *newUser)
{
    char buffer[BUFFERSIZE+1];
    int read_count;
    
    read_count = recv(client_sock, buffer, sizeof(buffer), 0);
    buffer[read_count -1] = '\0';
    
    if(uniqueUser(buffer))
    {
        int32_t c = htonl(1);
        if(send(client_sock, &c, sizeof(c), 0) == -1)
            printf("failure\n");
        return 1;

    }
    else
    {
        int32_t c = htonl(0);
        if(send(client_sock, &c, sizeof(c), 0) == -1)
            printf("failure\n");
    }
    strcpy(newUser->userName, buffer);
    read_count = recv(client_sock, buffer, sizeof(buffer), 0);
    buffer[read_count -1] = '\0';
    strcpy(newUser->firstName, buffer);
    read_count = recv(client_sock, buffer, sizeof(buffer), 0);
    buffer[read_count -1] = '\0';
    strcpy(newUser->lastName, buffer);
    read_count = recv(client_sock, buffer, sizeof(buffer), 0);
    buffer[read_count -1] = '\0';
    strcpy(newUser->password, buffer);
    
    //saves user to file
    int fd = 0;

    fd = open("users.info", O_WRONLY|O_CREAT | O_APPEND, S_IRWXU);
    
    struct userInfo temp = *newUser;
    
    size_t wrote = write(fd, &temp, sizeof(struct userInfo));
    
    close(fd);
    newUser->next = NULL;
    appendUser(newUser);
    return 0;
}

int uniqueUser(char userName[BUFFERSIZE+1])
{
    struct userInfo load;
    int fd = 0;
    fd = open("users.info", O_RDONLY | O_CREAT, S_IRWXU);
    
    size_t rd = read(fd, &load, sizeof(struct userInfo));
    while (rd > 0)
    {
        if (strcmp(load.userName, userName) == 0)
        {
            return 1;
        }
        rd = read(fd, &load, sizeof(struct userInfo));
    }
    return 0;
}

//Appends a new user struct to the linked list
void appendUser(struct userInfo* newInfo)
{
    pthread_mutex_lock(&list_lock);
    struct userInfo* temp = list;
    if (list == NULL)
    {
        list = newInfo;
        pthread_mutex_unlock(&list_lock);
        return;
    }

    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    temp->next = newInfo;
    pthread_mutex_unlock(&list_lock);
}


int login(int client_sock, struct userInfo* temp)
{
    char username[BUFFERSIZE+1];
    int read_count = recv(client_sock, username, sizeof(username), 0);
    username[read_count -1] = '\0';
    
    char pass[BUFFERSIZE+1];
    read_count = recv(client_sock, pass, sizeof(pass), 0);
    pass[read_count-1] = '\0';
    
    struct userInfo load;
    int fd = 0;
    fd = open("users.info", O_RDONLY);

    size_t rd = read(fd, &load, sizeof(struct userInfo));
    while (rd > 0)
    {
        if (strcmp(load.userName, username) == 0 && strcmp(load.password, pass) == 0)
        {
            
            *temp = load;
            temp->socket = client_sock;
            temp->next = NULL;
            appendUser(temp);
            int32_t c = htonl(0);
            if(send(client_sock, &c, sizeof(c), 0) == -1)
                printf("failuread\n");
            close(fd);
            
            printf("userName: %s", load.userName);
            return 0;
        }
        rd = read(fd, &load, sizeof(struct userInfo));
    }
    
    int32_t c = htonl(1);
    if(send(client_sock, &c, sizeof(c), 0) == -1)
        printf("fail\n");
    close(fd);
    return 1;
}


int checkifLoggedin(struct userInfo *newUser)
{
    struct userInfo* temp = list;
    while (temp != NULL)
    {
        if(strcmp(newUser->userName, temp->userName) == 0)
        {
            return 1;
        }
        temp = temp->next;
    }
    return 0;
}


void copyUserInfo(struct userInfo *dest, struct userInfo *source)
{
    strcpy(dest->userName, source->userName);
    strcpy(dest->firstName, source->firstName);
    strcpy(dest->lastName, source->lastName);
    strcpy(dest->password, source->password);
    dest->socket = source->socket;
    dest->next = NULL;
}

//Removes a user from the linked list
void removeUser(char user[BUFFERSIZE])
{
    pthread_mutex_lock(&list_lock);
    struct userInfo* temp = list;
    struct userInfo* prev = NULL;
    
    if(strcmp(list->userName, user) == 0)
    {
        prev = list;
        list = list->next;
        pthread_mutex_unlock(&list_lock);
        return;
    }
    while (temp != NULL && strcmp(temp->userName, user) != 0)
    {
        prev = temp;
        temp = temp->next;
    }
    if (temp != NULL && strcmp(temp->userName, user) == 0)
    {
        prev->next = temp;
    }
    pthread_mutex_unlock(&list_lock);
}


// ======= HELP FUNCTIONS =========== //
/* the following is a function designed for testing.
 it prints the ip address and port returned from
 getaddrinfo() function */

void *get_in_addr(struct sockaddr * sa) {
    if (sa->sa_family == AF_INET) {
        printf("ipv4\n");
        return &(((struct sockaddr_in *)sa)->sin_addr);
    }
    else {
        printf("ipv6\n");
        return &(((struct sockaddr_in6 *)sa)->sin6_addr);
    }
}

