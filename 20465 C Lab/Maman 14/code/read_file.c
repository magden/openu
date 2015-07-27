/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : output.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This converts the raw script files to input_command struct
 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "hash.h"
#include "consts.h"
#include "init.h"
#include "read_file.h"
#include "utils.h"

extern Hash errors;


/* the function get a char * type which
 * is a line of a file and an Input_Command which
 * is a structure of commands defined in init.h file
 * parse the line by splitting it into words and put
 * each word in a structure
 */
int work_with_str(char * line,Input_Command *command)
{
	/* to search for the words */
	char * pch;
	char * workaround;
	char *words[81];
	/*count the words*/
	int counter=0;
	int len;
	char * rdot;
	int i;
	int check = 0;
	/*for logic of the loop*/
	int check_status=0;
	/*count the lines of memory*/
	static int cline =1;

	command->data = (int *)malloc(sizeof(int)*80);
	for(i=0;i<80;i++)
	{
		words[i]="A";
	}
	/*split the line to words by spaces*/
	pch = strtok (line," \t\n");
	while (pch != NULL)
	{
		words[counter] = pch;
		if(strstr(pch,";") && counter == 0)
		{
			return -1;
		}
		pch = strtok (NULL, " ,\t\n");
		counter = counter + 1;
	}
	if(counter==0)
	{
		return -1;
	}
	/*check if the first word is a label */
	if(counter >0)
	{
		/*is it contain : */
		if (strstr(words[0],":"))
		{
			command->label = new_string(80);
			len = strlen(words[0]);
			rdot = words[0];
			*(rdot+ len) = '\0';
			strcpy(command->label,rdot);
			command->label[strlen(command->label)-1]=0;
			check_status = 1;
			/*count the memory line by one*/
			command->line = cline;
			cline = cline+1;
		}
		/*if command_countnot its a command*/
		else
		{
			/*count the memory line by one*/
			command->line = cline;
			cline = cline+1;
			/*if the first label is entry*/
			if (strstr(words[0],".entry") && counter >= 0)
			{
				command->command = new_string(80);
				command->entry = new_string(80);
				strcpy(command->entry, words[1]);
				strcpy(command->command,words[0]);
				return 1;
			}
			if (strstr(words[0],".extern") && counter >= 0)
			{
				command->command = new_string(80);
				command->entry = new_string(80);
				strcpy(command->entry, words[1]);
				strcpy(command->command,words[0]);
				return 1;
			}
			/*if the first label is data*/
			if (strstr(words[0],".data") && counter >= 0)
			{
				command->command = new_string(80);
				strcpy(command->command,words[0]);
				for (i=0;i<counter;i++)
				{
					command->data[i] = atoi(words[i+1]);
				}
				command->data_size=i-1;

				return 1;
			}
			if (strstr(words[1],".data") && counter >= 0)
			{
				command->command = new_string(80);
				strcpy(command->command,words[1]);
				for (i=0;i<counter;i++)
				{
					command->data[i] = atoi(words[i+1]);
				}
				command->data_size=i-1;

				return 1;
			}
			command->command = new_string(80);
			strcpy(command->command, words[0]);
		}

	}
	if(counter >= 1)
	{
		/*if the first line was a label then
		 * the second must be a command
		 */
		if (check_status == 1)
		{
			/*is it a string? */
			if (strstr(words[1],".string") && counter >= 2)
			{
				workaround = strstr(words[2],"\"");
				counter = 0;
				while (workaround != NULL){
				        counter++;
				        workaround = strstr(workaround+1,"\"");
				 }
				if (counter == 2)
				{
					command->command = new_string(80);
					strcpy(command->command, words[1]);
					pch = strtok (words[2],"\"");
					counter =0;
					while (pch != NULL)
					{
						words[counter] = pch;
						pch = strtok (NULL, " ,\t\n");
						counter = counter + 1;
					}
				command->string = new_string(80);
				strcpy(command->string,words[0]);
				return 1;
				}
				else
				{
					char* msg = new_string(80);
					strcpy(msg,"The string does not conatin braces");
					hash_add_item(errors,0,msg);
				}
			}
			/*is it an enry? */
			if (strstr(words[1],".entry") && counter >= 2)
			{
				command->command = words[2];
				command->entry = words[1];
				return 1;
			}
			if (strstr(words[1],".data") && counter >= 0)
			{
				command->command = new_string(80);
				strcpy(command->command,words[1]);
				for (i=2;i<counter;i++)
				{
					command->data[i-2] = atoi(words[i]);
				}
				command->data_size=1;

				return 1;
			}
			command->command = new_string(80);
			strcpy(command->command,words[1]);
			check_status = 2;
		}
		/*is it a special operand?*/
		else if (strstr(words[1],"#") )
		{
			workaround = words[1];
			workaround++;
			command->op1_type= "#";
			command->op1 = new_string(80);
			strcpy(command->op1, workaround);
			if (strcmp(words[2],"A") != 0)
			{
				command->op2 = new_string(80);
				strcpy(command->op2, words[2]);
				return 1;
			}
		}
		else if (strstr(words[1],"*") )
		{
			workaround = words[1];
			workaround++;
			command->op1_type= "*";
			command->op1 = new_string(80);
			strcpy(command->op1, workaround);
			if (words[2]!=NULL)
			{
				command->op2 = new_string(80);
				strcpy(command->op2, words[2]);
			}
		}
		else
		{
			if (strcmp(words[1], "A"))
			{
				command->op1 = new_string(80);
				strcpy(command->op1, words[1]);
			}
			if (strcmp(words[2], "A"))
			{
				command->op2 = new_string(80);
				strcpy(command->op2, words[2]);
			}
		}
		if (check_status == 1)
		{
			if (strstr(words[2],"#") )
			{
				workaround = words[2];
				workaround++;
				command->op1_type = new_string(2);
				strcpy(command->op1_type,"#");
				command->op1 = new_string(80);
				strcpy(command->op1, workaround);
			}
			else if (strstr(words[2],"*") )
			{
				workaround = words[2];
				workaround++;
				command->op1_type = new_string(2);
				strcpy(command->op1_type,"*");
				command->op1 = new_string(80);
				strcpy(command->op1, workaround);
			}
			else
			{
				command->op1 = new_string(80);
				strcpy(command->op1, words[2]);
				command->op2 = new_string(80);
				strcpy(command->op2, words[2]);
			}
		}
	}
	if(counter >= 2)
	{
		if (check_status == 2)
		{
			if (strstr(words[2],"#") )
			{
				workaround = words[2];
				workaround++;
				command->op1_type = new_string(2);
				strcpy(command->op1_type,"#");
				command->op1 = new_string(80);
				strcpy(command->op1, workaround);
				if (strcmp(words[3],"A"))
				{
					command->op2 = new_string(80);
					strcpy(command->op2, words[3]);
					return 1;
				}
			}
			else if (strstr(words[2],"*") )
			{
				workaround = words[2];
				workaround++;
				command->op1_type = new_string(2);
				strcpy(command->op1_type,"*");
				command->op1 = new_string(80);
				strcpy(command->op1, workaround);
			}
			else
			{
				if (strcmp(words[2],"A") != 0)
				{
				command->op1 = new_string(80);
				strcpy(command->op1, words[2]);
				}
				if (strcmp(words[3],"A") != 0)
				{
				command->op2 = new_string(80);
				strcpy(command->op2, words[3]);
				}
				check = 1;

			}
			if(counter != 0 && check == 0)
			{
			workaround = words[3];
			workaround++;
			command->op2_type = new_string(2);
			strcpy(command->op2_type,"*");
			command->op2 = new_string(80);
			strcpy(command->op2, workaround);
			}
		}
	}
	return 1;
}

/*parse the line by spliting it into words and put
 * each word in a structure
 */
Input_Command* read_file(char* filename,int*command_count)
				{
	/*count the line number*/

	int line_number=1;
	/*count the excuall line in memory*/
	int count_line = 0;
	/*allocate memory for parsing the commands into a struct*/
	Input_Command *commands = (Input_Command *)malloc(sizeof(Input_Command) *100);
	*command_count =0;
	if (commands == NULL)
	{
		char* msg = new_string(80);
		sprintf(msg, "sorry not enough memory to allocate");
		hash_add_item(errors,0,msg);
		exit(1);
	}
	/*open the file for reading*/
	FILE *file = fopen ( filename, "r" );
	/*check if the file exist*/
	if ( file != NULL )
	{
		char line [ 100 ]; /* or other suitable maximum line size */
		/*read 100 lines at a time , break if \n or if it reached to 100 characters*/
		while ( fgets ( line, sizeof line, file ) != NULL ) /* read a line */
		{
			/*is the line bigger then 80 characters? */
			if (strlen(line) > 80)
			{
				char* msg = new_string(80);
				sprintf(msg,"Line number %d is Too Long need to be 80 characters or less!\n", line_number);
				hash_add_item(errors,0,msg);
			}
			else
			{
				/*init the struct to null and zeroes*/

				memset(&commands[line_number-1], 0, sizeof(Input_Command));
				count_line = work_with_str(line,&commands[line_number-1]);
			}
			if (count_line > -1)
			{
				line_number = line_number+1;
				*command_count = *command_count+1;
			}
		}
		fclose ( file );
	}
	else
	{
		/* why didn't the file open? */
		printf("File %s not found!",filename);
	}
	return commands;
}
