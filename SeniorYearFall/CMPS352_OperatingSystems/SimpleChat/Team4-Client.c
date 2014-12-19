/*
 * Program:    Team Assignment 7
 * Authors:    Ronnie Passaro
 *             Adam Miller
 *             Eamonn Orr
 * Date:       November 7, 2014
 * File Name:  Team4-Client.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>
#include <time.h>

#define PORT 32400
#define MAXSIZE 128

struct clientNames {
    char client1Name[(MAXSIZE+1)*2];
    char client2Name[(MAXSIZE+1)*2];
};

struct inviteLog {
    char userInviteLog[MAXSIZE];
    struct inviteLog* next;
};

struct blockList {
    char userName[MAXSIZE];
    struct blockList* next;
};

char invUser[MAXSIZE];

void start_sendThread(int socket_fd);
void *sendThread(void * socket_fd_as_ptr);
void login_Or_Register(int socket_fd);
void invite_Or_Exit(int socket_fd, char buffer[]);
void get_ClientName(int socket_fd, struct clientNames *names);
void sendMessage(int socket_fd, char buffer[]);
void sendData(int socket_fd, char buffer[]);
void receiveMessage(int socket_fd, int clientNum, struct clientNames *names);
int receiveData(int socket_fd);
int checkMessage(char buffer[]);
struct inviteLog *invLog;
struct blockList *blkList;

int main(int argc, char *argv[])
{
    struct sockaddr_in server_info;
    struct hostent *he;
    int socket_fd, clientNum;
    struct clientNames *names = (struct clientNames* )malloc(sizeof(struct clientNames));
    invLog = NULL;
    blkList = NULL;
    
    
    if (argc != 2) {
        printf("usage: hostname\n");
        exit(1);
    }
    
    if ((he = gethostbyname(argv[1])) == NULL) {
        printf("Cannot get host name\n");
        exit(1);
    }
    
    // Get a socket
    if ((socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        printf("Socket failure \n");
        exit(1);
    }
    
    // Connect to the server using ip and port
    memset(&server_info, 0, sizeof(server_info));
    server_info.sin_family = AF_INET;
    server_info.sin_port = htons(PORT);
    server_info.sin_addr = *((struct in_addr *)he->h_addr);
    if (connect(socket_fd, (struct sockaddr *)&server_info, sizeof(struct sockaddr)) < 0) {
        printf("Connection Failure\n");
        exit(1);
    }
    
    login_Or_Register(socket_fd);
    
    get_ClientName(socket_fd, names);
    
    start_sendThread(socket_fd);
    
    while (1) {
        receiveMessage(socket_fd, clientNum, names);
    }
}



void start_sendThread(int socket_fd) {
    pthread_t pthread;
    long socket_fd_long = socket_fd;
    if (pthread_create(&pthread, NULL, sendThread, (void *)socket_fd_long) != 0) {
        printf("Failed to start send thread\n");
    }
}

void * sendThread(void * socket_fd_as_ptr) {
    char buffer[MAXSIZE];
    char * check = "/";
    long socket_fd = (long) socket_fd_as_ptr;
    
    printf("Enter \"/INVITE <username>\" to invite\n");
    printf("Enter \"/EXIT\" to exit the chat room\n");
    printf("Enter \"/QUIT\" to quit the program\n");
    printf("Enter \"/LOG\" to view the invite log\n");
    
    while (1) {
        // Client message to send
        fgets(buffer,MAXSIZE-1,stdin);
        
        if (strncasecmp(check, buffer, 1) == 0) {
            invite_Or_Exit((int)socket_fd, buffer);
        }
        else {
            if (checkMessage(buffer) == 1) {
                sendMessage((int)socket_fd, buffer);
            }
        }
    }
    return NULL;
}

void invite_Or_Exit(int socket_fd, char buffer[]) {
    char * invite = "/invite ";
    char * exitChat = "/exit";
    char * quitChat = "/quit";
    char * yes = "/yes";
    char * no = "/no";
    char * logCheck = "/log";
    char * block = "/block";
    
    if (strncasecmp(invite, buffer, 8) == 0) {
        sendData(socket_fd, buffer);
    }
    else if (strncasecmp(logCheck, buffer, 4) == 0) {
        struct inviteLog *tempBuff = invLog;
        while (tempBuff != NULL) {
            printf("%s\n", tempBuff->userInviteLog);
            tempBuff = tempBuff->next;
        }
    }
    else if (strncasecmp(block, buffer, 6) == 0) {
        
        struct blockList *myList = (struct blockList*) malloc(sizeof(struct blockList));
        char * user = strchr(buffer, ' ') + 1;
        strncpy(myList->userName, user, strlen(user) - 1);
        
        struct blockList *tempBuff = blkList;
        
        if (blkList == NULL) {
            blkList = myList;
            printf("Block List: %s\n", myList->userName);
            return;
        }
        while (tempBuff->next != NULL) {
            tempBuff = tempBuff->next;
            
        }
        tempBuff->next = myList;
        struct blockList *temp2Buff = blkList;
        while (temp2Buff != NULL) {
            printf("Block List: %s\n", temp2Buff->userName);
            temp2Buff = temp2Buff->next;
        }
    }
    else if (strncasecmp(exitChat, buffer, 5) == 0) {
        sendData(socket_fd, buffer);
        printf("Exit current chat\n");
    }
    else if (strncasecmp(quitChat, buffer, 5) == 0) {
        sendData(socket_fd, buffer);
        printf("Quit chat\n");
        exit(0);
    }
    else if (strncasecmp(yes, buffer, 4) == 0) {
        sendData(socket_fd, buffer);
        invLog = NULL;
    }
    else if (strncasecmp(no, buffer, 3) == 0) {
        if (strlen(buffer) - 1 != 3) {
            printf("here\n");
            sendData(socket_fd, buffer);
            struct inviteLog *tempBuff = invLog;
            struct inviteLog *temp2Buff = invLog;
            if (tempBuff->next == NULL) {
                invLog = NULL;
            }
            else {
                while (tempBuff != NULL) {
                    if (tempBuff->userInviteLog == invUser) {
                        temp2Buff = tempBuff;
                    }
                    else {
                        temp2Buff = temp2Buff->next;
                    }
                    tempBuff = tempBuff->next;
                }
            }
        }
        else {
            struct inviteLog *tempBuff = invLog;
            while (tempBuff != NULL) {
                char noSend[MAXSIZE+1] = "/no ";
                strcat(noSend, tempBuff->userInviteLog);
                if ((send(socket_fd, noSend, sizeof(noSend), 0)) == -1) {
                    printf("Failure Sending Message\n");
                    close(socket_fd);
                    exit(1);
                }
                tempBuff = tempBuff->next;
            }
            invLog = NULL;
        }
    }
}



int loginUser(int socket_fd, char buff[]) {
    printf("Enter username: ");
    fgets(buff, MAXSIZE-1, stdin);
    sendData(socket_fd, buff);
    printf("Enter password: ");
    fgets(buff, MAXSIZE-1, stdin);
    sendData(socket_fd, buff);
    
    int32_t start = 0;
    long num = recv(socket_fd, &start, sizeof(start), 0);
    if (num <= 0) {
        printf("Error receiveing client number\n");
        exit(1);
    }
    int32_t temp = ntohl(start);
    
    if (temp == 1) {
        printf("Invalid Username or Password\n");
        return temp;
    }
    else if (temp == 0) {
        printf("Login Successful\n");
    }
    else {
        printf("Error\n");
    }
    
    return temp;
}

void registerUser (int socket_fd, char buff[]) {
    int uniqueUser = 1;
    while (uniqueUser != 0) {
        printf("Enter username: ");
        fgets(buff, MAXSIZE-1, stdin);
        sendData(socket_fd, buff);
        uniqueUser = receiveData(socket_fd);
    }
    printf("Enter first name: ");
    fgets(buff, MAXSIZE-1, stdin);
    sendData(socket_fd, buff);
    printf("Enter last name: ");
    fgets(buff, MAXSIZE-1, stdin);
    sendData(socket_fd, buff);
    printf("Enter password: ");
    fgets(buff, MAXSIZE-1, stdin);
    sendData(socket_fd, buff);
}

void login_Or_Register(int socket_fd) {
    char buff[MAXSIZE];
    int i = 0;
    while (i == 0) {
        printf("Enter 0 or 1\n0 = Login\n1 = Register\n->");
        fgets(buff, MAXSIZE-1, stdin);
        
        if (buff[0] == '0') {
            int32_t choice = htonl(0);
            if ((send(socket_fd, &choice, sizeof(choice), 0)) == -1) {
                printf("Failure Sending Message\n");
                close(socket_fd);
                exit(1);
            }
            if (loginUser(socket_fd, buff) == 0) {
                i = 1;
            }
        }
        else if (buff[0] == '1') {
            int32_t choice = htonl(1);
            if ((send(socket_fd, &choice, sizeof(choice), 0)) == -1) {
                printf("Failure Sending Message\n");
                close(socket_fd);
                exit(1);
            }
            registerUser(socket_fd, buff);
            i = 1;
        }
        else {
            printf("You didn't enter 0 or 1\n");
        }
    }
}




void get_ClientName(int socket_fd, struct clientNames *name) {
    
    // Receive first and last name of the client
    long num = recv(socket_fd, name->client1Name, sizeof(name->client1Name), 0);
    if ( num <= 0 ) {
        printf("Error receiveing message\n");
        exit(1);
    }
    name->client1Name[num] = '\0';
    printf("%s logged in\n", name->client1Name);
}



int checkMessage(char buffer[]) {
    
    // Checks to see if the message is at least 1 character and less then 128
    if (strlen(buffer) <= 1) {
        printf("Message must be at least 1 character\n");
        return 0;
    }
    else if (strlen(buffer) > 128) {
        printf("Message can't me greater then 128 characters\n");
        return 0;
    }
    else {
        // No errors
        return 1;
    }
    
}



void sendMessage(int socket_fd, char buffer[]) {
    if ((send(socket_fd,buffer, strlen(buffer), 0)) == -1) {
        printf("Failure Sending Message\n");
        close(socket_fd);
        exit(1);
    }
}


void sendData(int socket_fd, char buffer[]) {
    if ((send(socket_fd,buffer, strlen(buffer), 0)) == -1) {
        printf("Failure Sending Message\n");
        close(socket_fd);
        exit(1);
    }
}



void receiveMessage(int socket_fd, int clientNum, struct clientNames *name) {
    char buff[MAXSIZE];
    long num = recv(socket_fd, buff, sizeof(buff), 0);
    if ( num <= 0 ) {
        printf("Error receiveing message\n");
        exit(1);
    }
    
    char * check = "/";
    char * check2 = "//";
    char * exitCheck = "/ext";
    char * quitCheck = "/quit";
    char * yeaCheck = "/yea";
    if (strncasecmp(exitCheck, buff, 4) == 0) {
        if ((send(socket_fd, buff, sizeof(buff), 0)) == -1) {
            printf("Failure Sending Message\n");
            close(socket_fd);
            exit(1);
        }
    }
    else if (strncasecmp(quitCheck, buff, 5) == 0) {
        close(socket_fd);
        exit(0);
    }
    else if (strncasecmp(yeaCheck, buff, 4) == 0) {
        printf("buff: %s\n", buff);
        num = recv(socket_fd, buff, sizeof(buff), 0);
        if ( num <= 0 ) {
            printf("Error receiveing message\n");
            exit(1);
        }
        printf("yea buff: %s\n", buff);
        buff[num] = '\0';
        strcpy(invUser, buff);
        printf("invUser: %s\n", invUser);
        char yesSend[MAXSIZE] = "/yes ";
        strcat(yesSend, invUser);
        printf("yessend: %s\n", yesSend);
        if ((send(socket_fd, yesSend, sizeof(yesSend), 0)) == -1) {
            printf("Failure Sending Message\n");
            close(socket_fd);
            exit(1);
        }
    }
    else if (strncasecmp(check2, buff, 2) == 0) {
        buff[num] = '\0';
        printf("%s\n", buff);
        num = recv(socket_fd, buff, sizeof(buff), 0);
        if ( num <= 0 ) {
            printf("Error receiveing message\n");
            exit(1);
        }
        buff[num] = '\0';
        strcpy(invUser, buff);
        
        
        char yesSend[MAXSIZE] = "/yup ";
        strcat(yesSend, invUser);
        
        if ((send(socket_fd, yesSend, sizeof(yesSend), 0)) == -1) {
            printf("Failure Sending Message\n");
            close(socket_fd);
            exit(1);
        }
    }
    else if (strncasecmp(check, buff, 1) == 0) {
        buff[num] = '\0';
        printf("%s\n", buff);
        num = recv(socket_fd, buff, sizeof(buff), 0);
        if ( num <= 0 ) {
            printf("Error receiveing message\n");
            exit(1);
        }
        buff[num] = '\0';
        strcpy(invUser, buff);
        
        
        struct blockList *tempBuff2 = blkList;
        while (tempBuff2 != NULL) {
            
            if (strcmp(invUser, tempBuff2->userName) == 0) {
                printf("blocked user, sending no\n");
                char noSend[MAXSIZE] = "/no ";
                strcat(noSend, invUser);
                
                if ((send(socket_fd, noSend, sizeof(noSend), 0)) == -1) {
                    printf("Failure Sending Message\n");
                    close(socket_fd);
                    exit(1);
                }
            }
            else {
                
            }
            tempBuff2 = tempBuff2->next;
            
        }
        
        struct inviteLog *myLog = (struct inviteLog*) malloc(sizeof(struct inviteLog));
        strcpy(myLog->userInviteLog, invUser);
        
        struct inviteLog *tempBuff = invLog;
        
        if (invLog == NULL) {
            invLog = myLog;
            return;
        }
        while (tempBuff->next != NULL) {
            tempBuff = tempBuff->next;
            
        }
        tempBuff->next = myLog;
    }
    else {
        // The received may not end with a '\0'
        buff[num] = '\0';
        printf("%s\n", buff);
    }
}

int receiveData(int socket_fd) {
    
    int32_t start = 0;
    long num = recv(socket_fd, &start, sizeof(start), 0);
    if ( num <= 0 ) {
        printf("Error receiveing client number\n");
        exit(1);
    }
    int32_t temp = ntohl(start);
    
    if (temp == 1) {
        printf("Username already used\n");
    }
    else if (temp == 0) {
        printf("Unique username\n");
    }
    else {
        printf("Error\n");
    }
    
    return temp;
}

