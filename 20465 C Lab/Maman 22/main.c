/*
 * main.c
 *
 *  Created on: May 19, 2012
 *      Author: stas
 */

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include "set.h"

typedef struct{
   const char*name;
   const void*key;
}Tbl[];

void*map(char*name,Tbl tbl){
    for( ; tbl->name && strcmp(name,tbl->name); tbl++)
        ;
    return (void*)tbl->key;
}
 
void read_set_io(char*args)
{
	printf("read set io\n%s\n",args);
}
void print_set_io(char*args)
{
	/* get set */
	printf("%s\n",args);
}
void union_set_io(char*args)
{
	
}
void intersect_set_io(char*args)
{
	
}
void sub_set_io(char*args)
{

}

void halt()
{
	exit(0);
}

void read_command(char*input,char*command,char*args)
{
	char*token = strtok(input,", ");
	strcpy(command,token);

	token = strtok (NULL, ", ");
	strcpy(args,token);
	while (token)
	{
	    /* printf ("%s\n",token);*/
	    token = strtok (NULL, ", ");
	}
}


int main()
{
	/* init commands */
	Set A,B,C,D,E,F;
	/* const Tbl sets={{"SET_A",A},{"SET_B",B},{"SET_C",C},{"SET_D",D},{"SET_E",E},{"SET_F",F},{NULL,NULL}}; */
	const Tbl cmds={{"read_set",read_set_io},{"print_set",print_set_io},{"union_set",union_set_io},{"intersect_set",intersect_set_io},{"sub_set",sub_set_io},{"halt",halt},{NULL,NULL}};

	/* init sets */
	A=new_set();
	B=new_set();
	C=new_set();
	D=new_set();
    E=new_set();
	F=new_set();
	
	char*input = malloc(sizeof(char)*MAX_INPUT_SIZE);
	char*command = malloc(sizeof(char)*MAX_COMMAND_SIZE);
	char*args = malloc(sizeof(char)*(MAX_INPUT_SIZE-MAX_COMMAND_SIZE));

	while(1)
	{
		printf(PROMPT);
		gets(input);
		read_command(input,command,args);

		void*(*f)(void*) = map(command,cmds);
		if(f==NULL)
			printf("No such command‬‬ '%s'\n",command);
		else
			f(args);
	}

	/* clear memory */
	free(A);
	free(B);
	free(C);
	free(D);
	free(E);
	free(F);
	return 0;
}
