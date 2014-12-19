/* 	Program:	Hello World
	author:		Adam Miller
	Date:		Aug. 26th, 2014
	file name:	hello_world.c
	compile:	gcc -o helloworld hello_world.c
	run:		./helloworld
	
	This C program accepts user's first name and a positive integer N; 
	it then prints a personal greeting N times
*/

#include <stdio.h>

int main() 
{
	char name[20];		// A string of <= 19 chars
	int times;
	int i = 0;
	
	printf("Please enter your first name and a number:\n");
	scanf("%s%d", name, &times);
	
	// print greeting
	for(i = 0; i < times; i++)
	{
		printf("Hello %s, your first program works!\n", name);
	}
}