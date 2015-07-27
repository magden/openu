/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : main.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : main assembler routine. It reads and compiles .as files
 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "hash.h"
#include "consts.h"
#include "init.h"
#include "read_file.h"
#include "firstScan.h"
#include "secondScan.h"
#include "utils.h"
#include "output.h"

extern Hash errors;
extern Hash symbols;
extern Hash data_symbols;
extern Hash externals;
extern Hash entry;

void print_errors()
{
	HashNode*node;
	for_each_item(node,errors)
	{
		printf("  - %s\n",node->value);
	}
	printf("\n");
}

void clear_hashes()
{
	hash_clear(errors);
	hash_clear(symbols);
	hash_clear(data_symbols);
	hash_clear(externals);
	hash_clear(entry);
}

int main(int argc, char**argv)
{
	int i,IC,DC,command_count;
	Input_Command *commands;
	Output_Command* output_cmd;
	init();

	if(argc==1)
	{
		printf("No files to compile");
	}

	for(i=1; i<argc; i++)
	{
		/* add .as in thte end of the filename */
		char*file_name = new_string(strlen(argv[i])+3);
		strcpy(file_name,argv[i]);
		strcat(file_name,".as");

		printf("Compiling file %s ... ",file_name);

		/* reset IC & DC */
		IC = DC = 0;

		/* clean hashes for this run */
		clear_hashes();

		/*read the file */
		commands = read_file(file_name,&command_count);

		/*first scan */
		first_scan(commands,command_count,&IC,&DC);

		/* if there are any errors. abort and show the errors */
		if(!hash_empty(errors))
		{
			printf("Failed\n");
			print_errors();
			continue;
		}

		/* do not compile empty scripts */
		if(command_count<=0)
			continue;

		/* second scan */
		output_cmd = second_scan(commands,command_count,IC,DC);

		/* if there are any errors. abort and show the errors */
		if(!hash_empty(errors))
		{
			printf("Failed\n");
			print_errors();
			continue;
		}

		/*create the output files */
		output_all(argv[i],output_cmd,IC,DC);
		printf("Ok!\n");
	}
	return 0;
}
