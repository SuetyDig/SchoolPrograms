#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <string.h>

int main()
{
    int pid = 0;
    int i = 0;
    char *tmp;
    char instring[80];      //Stores one line of input(max of 80 characters)
    char *argv[10];         //Stores up to ten arguments
    char *login;
    
    login = getlogin();
    while (1 == 1)
    {
        printf("%s: ", login);
        //Gets the input from the keyboard
        i = 0;
        instring[i] = getc(stdin);
        while (instring[i] != '\n')
        {
            i++;
            instring[i] = getc(stdin);
        }
        instring[i] = '\0';
        
        //Separates the input into arguments
        tmp = instring;
        i = 0;
        argv[i] = strsep(&tmp, " \t");
        while (i < 10 && argv[i] != '\0')
        {
            i++;
            argv[i] = strsep(&tmp, " \t");
        }
        
        if(strcmp(argv[0], "exit") == 0)
        {
            printf("logout\n\n[Process Completed]\n");
            exit(0);
        } else if(strcmp(argv[0], "cd") == 0 || strcmp(argv[0], "chdir") == 0)
        {
            chdir(argv[1]);
        } else  if((pid = fork()) == 0)
        {
            if(execvp(argv[0], argv))
            {
                printf("Error:");
                int j = 0;
                while(argv[j] != '\0' && j < 10)
                {
                    printf(" %s", argv[j]);
                    j++;
                }
                printf("\n");
            }
            exit(0);
        }
        else
        {
            int status;
            waitpid(0, &status, 0);
        }
        
    }
}
