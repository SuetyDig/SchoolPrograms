#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

struct process
{
    int processID;
    char owner[25];
    char programName[25];
    int totalTime;
    int timeLeft;
    int priority;
};

struct index
{
    int index;
    int processID;
    struct index *next;
};

typedef struct process Process;
typedef struct index Index;

void appendProcess(Process **startPtr, Process *newProcess);
void processIDSort(Index **indexList, Index *newIndex);
void sort(Index **indexList, Index *newIndex);
void printProcesses(int argc, char *argv[]);
int findProcessByID(Index **indexList, int pid);
void loadProcesses(Process proc[], int index, int argc, char *argv[]);
int loadIndex(Index **indexList, int argc, char *argv[]);
void saveProcess(Process proc[], int index, int argc, char *argv[]);
void saveIndex(Index **indexList, int argc, char *argv[]);

int main(int argc, char *argv[])
{
    int index = 0;
    //Changes if modifications were made to the file
    int mod = 0;
    //used to determine the command input
    char command;
    
    //Array of processes limited to 100
    Process proc[100];
    
    //Pointer to the first index in the list
    Index *indexList = NULL;
    
    //Placeholders for new processes and Indices
    Process aProcess;
    Index *newIndex = NULL;
    
    //Loads ProcessList and IndexList index list also receives total number of
    //processes add and stores it in index
    index = loadIndex(&indexList, argc, argv);
    loadProcesses(proc, index, argc, argv);
    
    //Receives command
    while(command != 'q')
    {
        printf("Enter a command: ");
        //Gets first command
        scanf("%s", &command);
        
        mod = 0;
        
        //Adds a process
        if(command == '+')
        {
            //Creates space for new process and index
            aProcess = *(Process *) malloc(sizeof(Process));
            newIndex = (Index *) malloc(sizeof(Index));
            
            scanf("%d%s%s%d%d%d", &(aProcess.processID), aProcess.owner, aProcess.programName, &(aProcess.totalTime), &(aProcess.timeLeft), &(aProcess.priority));
            
            //Checks if the process has been added already
            int check = findProcessByID(&indexList, aProcess.processID);
            
            //If the process hasn't been added it adds it to the end of the process array
            //Then sorts it inside the index
            if(check == -1)
            {
                proc[index] = aProcess;
                
                newIndex->index = index;
                newIndex->processID = aProcess.processID;
                
                processIDSort(&indexList, newIndex);
               
                mod = 1;
                index++;

                printf("ADD: %d, %s, %s, %d, %d, %d\n", aProcess.processID, aProcess.owner, aProcess.programName, aProcess.totalTime, aProcess.timeLeft, aProcess.priority);
            } else
            {
                printf("ERROR - pid exists\n");
            }
            
        //Modifies process' priority
        } else if(command == '*')
        {
            int pid = 0;
            int priority = 0;
            int oldpriority = 0;
            
            scanf("%d%d", &pid, &priority);
            
            int indx = findProcessByID(&indexList, pid);
            
            if(indx == -1) printf("ERROR - process does not exist\n");
            else
            {
                oldpriority = proc[indx].priority;
                proc[indx].priority = priority;
                
                printf("UPDATE: %d, old: %d, new: %d\n", proc[indx].processID, oldpriority, proc[indx].priority);
                mod = 1;
                
            }
            
        //Gets a process by ID
        } else if(command == '?')
        {
            int pid = 0;
            int indx = 0;
            
            scanf("%d", &pid);
            
            indx = findProcessByID(&indexList, pid);
            
            if(indx >= 0)
            {
                printf("QUERY: %d, %s, %s, %d, %d, %d\n", proc[indx].processID, proc[indx].owner, proc[indx].programName, proc[indx].totalTime,
                       proc[indx].timeLeft, proc[indx].priority);
            }
            else printf("ERROR - process does not exist\n");
            
            //Prints processes out in order from memory
        } else if(command == 'p')
        {
            printProcesses(argc, argv);
        } else if(command == 'q') printf("Program Terminated\n");
        else printf("Command not found\n");
        //Checks if file has been modified. If it has it saves the files
        if(mod == 1)
        {
            saveProcess(proc, index, argc, argv);
            saveIndex(&indexList, argc, argv);
        }
    }
    
}

//Prints process from saved file
void printProcesses(int argc, char *argv[])
{
    //Creates a char[] and scans input into it
    char fileName[25];
    scanf("%s", fileName);
    
    //Opens that file
    FILE *pr = fopen(fileName, "w");
    
    //Used to open .index and .master file
    int fdi = 0;
    int fdp = 0;
    ssize_t num1 = 1;
    ssize_t num2 = 0;
    
    int write = 0;
    
    //Placeholders for those two values
    int index;
    Process process;
    
    //Opens .index as a read-only value
    fdi = open("millera21.index", O_RDONLY);
    
    //Iterates through the indexes
    while (num1 >= 1)
    {
        write = (int) num2;
        
        //Finds the value of the index
        num1 = read(fdi, &index, sizeof(int));
        
        fdp = open("millera21.master", O_RDONLY);
        int i = 0;
        if(num1 >= 1)
        {
            //Uses index to determine how much data needs to be read in
            for (i = 0; i <= index; i++)
            {
                num2 = read(fdp, &process, sizeof(Process));
            }
            //Prints it to fileName
            fprintf(pr, "%d, %s, %s, %d, %d, %d\n", process.processID, process.owner, process.programName, process.totalTime, process.timeLeft, process.priority);
            
        }
        //Skips past the ProcessID
        num1 = read(fdi, &index, sizeof(int));
        
        close(fdp);
    }
    if (write <= 0) printf("File Empty\n");
    else printf("File Written\n");
    close(fdi);
    fclose(pr);
}

//Handles first case otherwise it calls sort
void processIDSort(Index **indexList, Index *newIndex)
{
    if(*indexList == NULL)
    {
        newIndex->next = NULL;
        *indexList = newIndex;
    } else
    {
        sort(indexList, newIndex);
    }
}

//Sorts Index based off of the processID as they come in
void sort(Index **indexList, Index *newIndex)
{
    Index *curr = *indexList;
    Index *prev = NULL;
    
    //Iterates through until the current index's processID is greater then the new one
    //Or until there is no next index
    while (curr->next != NULL && curr->processID < newIndex->processID)
    {
        prev = curr;
        curr = curr->next;
    }
    
    //Checks if its the second index added
    if (curr->next == NULL && prev == NULL)
    {
        if (curr->processID > newIndex->processID)
        {
            newIndex->next = curr;
            curr = newIndex;
            *indexList = curr;
        } else
        {
            curr->next = newIndex;
            newIndex->next = NULL;
        }
    }
    //Handles the situation where the index added has the highest processID
    else if(curr->next == NULL && curr->processID < newIndex->processID)
    {
        curr->next = newIndex;
        newIndex->next = NULL;
    }
    //Handles the situation where the index added has the lowest processID
    else if (prev == NULL)
    {
        newIndex->next = curr;
        curr = newIndex;
        *indexList = curr;
    }
    //Handles the situation where the index being added's processID falls in the middle
    else
    {
        prev->next = newIndex;
        newIndex->next = curr;
    }
}


//Finds a process by its ID and returns its index. If it's ID isn't found it returns -1
int findProcessByID(Index **indexList, int pid)
{
    Index *curr = *indexList;
    while (curr != NULL)
    {
        if (pid == curr->processID) return curr->index;
        curr = curr->next;
    }
    return -1;
}

//Loads the processes from the binary file
void loadProcesses(Process proc[], int index, int argc, char *argv[])
{
    int fd = 0;
    ssize_t num = 0;
    int i = 0;
    
    Process process;
    
    //Opens the file
    fd = open("millera21.master", O_RDONLY);
    
    //reads in the first process
    num = read(fd, &process, sizeof(process));
    if(num >= 1) proc[i] = process;
    
    //Iterates through the remaining processes
    for (i = 1; i < index; i++)
    {
        num = read(fd, &process, sizeof(Process));
        if (num >= 1) proc[i] = process;
    }
    
    close(fd);
}

//Loads the Index from the binary file(scanf is messing it up?)
int loadIndex(Index **indexList, int argc, char *argv[])
{
    int fd = 0;
    ssize_t num = 0;
    int indx = 0;
    int pid = 0; int index = 0;
    Index *ind = (Index *) malloc(sizeof(Index));

    //Opens .index
    fd = open("millera21.index", O_RDONLY);
    
    //Reads the first index in
    num = read(fd, &index, sizeof(int));
    if (num >= 1)
    {
        //Sets next to null and sets ind->index equal to the index that was read in
        ind->next = NULL;
        ind->index = index;
        
        //Reads in the first processID and sets ind->processID equal to the processID that was read in
        num = read(fd, &pid, sizeof(int));
        ind->processID = pid;
        
        //Has indexList point to ind
        *indexList = ind;
        
        //Increments the total index
        indx++;
    }
    
    //Iterates through the remaining indexes to be added
    while (num >= 1)
    {
        num = read(fd, &index, sizeof(int));
        if(num >= 1)
        {
            ind->next = (Index *) malloc(sizeof(Index));
            ind = ind->next;
            
            ind->next = NULL;
            ind->index = index;
            num = read(fd, &pid, sizeof(int));
            ind->processID = pid;
            
            indx++;
        }
    }
    return indx;
    close(fd);
}

//Saves the process file
void saveProcess(Process proc[], int index, int argc, char *argv[])
{
    int i = 0;
    int fd = 0;
    ssize_t num = 0;
    Process process;
    
    //Opens .master
    fd = open("millera21.master", O_TRUNC|O_RDWR|O_CREAT, S_IRWXU);
    
    //Iterates through all the processes and writes them to the file
    for (i = 0; i < index; i++)
    {
        process = proc[i];
        num = write(fd, &process, sizeof(Process));
    }
    close(fd);
}

//Saves index
void saveIndex(Index **indexList, int argc, char *argv[])
{
    int fd = 0;
    ssize_t num = 0;
    Index *index = *indexList;
    
    //Opens .index
    fd = open("millera21.index", O_TRUNC|O_RDWR|O_CREAT, S_IRWXU);
    
    //Iterates through the indices and saves them to the file
    while (index != NULL)
    {
        num = write(fd, &index->index, sizeof(int));
        num = write(fd, &index->processID, sizeof(int));
        index = index->next;
    }
    close(fd);
}
