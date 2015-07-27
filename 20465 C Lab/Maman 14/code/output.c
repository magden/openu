/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : output.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : functions to print the output binary data to files
 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "hash.h"
#include "consts.h"
#include "utils.h"
#include "init.h"

extern Hash entry;
extern Hash externals;
extern int data[MEM_SIZE];

/* output the entry hash to a file called xxx.ent.ob
 * xxx is the given filename */
void output_entry(char*file_name)
{
	char* full_name = new_string(strlen(file_name)+4);
	strcpy(full_name,file_name);
	strcat(full_name,".ent");

	FILE *fp = fopen(full_name,"w");

	HashNode *node;
	for_each_item(node,entry)
	{
		fprintf(fp,"%s \t\t %o\n",node->value,node->key);
	}

	free(full_name);
	fclose(fp);
}


/* output the externals hash to a file called xxx.ext.ob
 * xxx is the given filename */
void output_extern(char*file_name)
{
	char* full_name = new_string(strlen(file_name)+4);
	strcpy(full_name,file_name);
	strcat(full_name,".ext");

	FILE *fp = fopen(full_name,"w");

	HashNode *node;
	for_each_item(node,externals)
	{
		if(node->key != NOT_SET)
			fprintf(fp,"%s \t\t %o\n",node->value,node->key);
	}

	free(full_name);
	fclose(fp);
}

/* output the binary command output from second scan to a text file called xxx.ob
 * xxx is the given file name
 */
void output_commands(char*file_name,Output_Command* output,int IC,int DC)
{
	int i;
	char* full_name = new_string(200);
	strcpy(full_name,file_name);
	strcat(full_name,".ob");

	FILE *fp = fopen(full_name,"w");

	/* print the output commands and their linker letter (a/r/e) */
	fprintf(fp,"     \t  %o %o\n", IC,DC);
	for(i=0;i<IC;i++)
	{
		fprintf(fp,"%04o \t %06o \t %c\n", i, to16bit(output[i].bin_command), output[i].linker_data);
	}

	/* print the .data and .string information from the data array */
	for(i=IC; i<IC+DC; i++)
	{
		fprintf(fp,"%04o \t %06o\n", i, to16bit(data[i-IC]));
	}

	free(full_name);
	fclose(fp);
}

/* Does the whole output process. outputs the second scan results -
 *  the entry (if exists), externals (if exists) and the binary commands into .ob files
 */
void output_all(char*file_name,Output_Command* output,int IC,int DC)
{
	/* output entries (if available) */
	if(!hash_empty(entry))
		output_entry(file_name);

	/* output externals only if there is at least 1 in use */
	int ext_used=FALSE;
	HashNode*node;
	for_each_item(node,externals)
	{
		if(node->key!=NOT_SET)
		{
			ext_used=TRUE;
			break;
		}
	}
	if(ext_used)
		output_extern(file_name);

	/* output the main command file */
	output_commands(file_name,output,IC,DC);
}
