#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

struct process
{
    int processID;
    char owner[25];
    char programName[25];
    int totalTime;
    int timeLeft;
    int priority;
    struct process *next;
};

typedef struct process Process;

void next(Process **startPtr, Process *newProcess);
void prioritySort(Process **startPtr, Process *newProcess);
void printProcesses(Process **startPtr);

int main()
{
    int totalProcess = 0;
    int i = 0;
    
    Process *start = NULL;
    Process *aProcess = NULL;
    
    scanf("%d", &(totalProcess));
    
    //ProcessID, Owner Name, Program Name, Execution time, time left, priority
    for(i = 0; i < totalProcess; i++)
    {
        aProcess = (Process *) malloc(sizeof(Process));
        scanf("%d%s%s%d%d%d", &(aProcess->processID), aProcess->owner, aProcess->programName, &(aProcess->totalTime), &(aProcess->timeLeft), &(aProcess->priority));
        next(&start, aProcess);
    }
    printProcesses(&start);
}

void next(Process **startPtr, Process *newProcess)
{
    if(*startPtr == NULL)
    {
        *startPtr = newProcess;
        return;
    }
    prioritySort(startPtr, newProcess);
}

void printProcesses(Process **startPtr)
{
    Process *temp = *startPtr;
    while (temp != NULL)
    {
        printf("%d, %s, %s, %d, %d, %d\n", temp->processID, temp->owner, temp->programName, temp->totalTime, temp->timeLeft, temp->priority);
        temp = temp->next;
    }
}

//Sorts Processes based off of priority as they come in
void prioritySort(Process **startPtr, Process *newProcess)
{
    Process *curr = *startPtr;
    Process *prev = NULL;
    
    //Iterates through until the current processes priority is less then the new one
    //Or until there is no next process
    while (curr->next != NULL && curr->priority < newProcess->priority)
    {
        prev = curr;
        curr = curr->next;
    }
    
    //Checks if its the second process added
    if (curr->next == NULL && prev == NULL)
    {
        if (curr->priority > newProcess->priority)
        {
            newProcess->next = curr;
            curr = newProcess;
            *startPtr = curr;
        } else
        {
            curr->next = newProcess;
            newProcess = NULL;
        }
    }
    //Handles the situation where the process added has the least priority
    else if(curr->next == NULL)
    {
        curr->next = newProcess;
        newProcess->next = NULL;
    }
    //Handles the situation where the process added has the most priority
    else if (prev == NULL)
    {
        newProcess->next = curr;
        curr = newProcess;
        *startPtr = curr;
    }
    ////Handles the situation where the process being added's priority falls in the middle
    else
    {
        prev->next = newProcess;
        newProcess->next = curr;
    }
}