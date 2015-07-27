/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : secondScan.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This is the second scan process. Its job is to check that all
 	 	 	   labels are exist, set the proper code referances to entry and
 	 	 	   entern hashes, combine symbols and data_symbols hashes
 	 	 	   and create the binary commands to output
 ============================================================================
 */

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "init.h"
#include "hash.h"
#include "consts.h"
#include "utils.h"
#include "firstScan.h"

extern Hash errors;
extern Hash commands;
extern Hash registers;
extern Hash operand_types;
extern Hash symbols;
extern Hash data_symbols;
extern Hash externals;
extern Hash entry;

/* translates the command to binary machine code*/
int build_binary_command(Input_Command inp)
{
	int bin_cmd,tmp_register;

	/* switch between op1 and op2 if op2 is NULL */
	if(inp.op2 == NULL && inp.op1 != NULL)
	{
		inp.op2 = inp.op1;
		inp.op2_type = inp.op1_type;
		inp.op1=NULL;
		inp.op1_type = NULL;
	}

	/* command */
	bin_cmd = hash_get_key(commands,inp.command);
	bin_cmd <<= 3;

	/* operand 1 + type */
	if(inp.op1!=NULL)
	{
		/* operand type 1 */
		bin_cmd += get_operand_type(inp.op1_type,inp.op1);
		bin_cmd <<= 3;

		/* operand 1 */
		tmp_register = hash_get_key(registers,inp.op1);
		if(tmp_register != NOT_EXIST)
			bin_cmd+=tmp_register;
		bin_cmd<<=3;
	}
	else
	{
		bin_cmd<<=6;
	}

	/* operand 2 + type */
	if(inp.op2 != NULL)
	{
		/* operand type 2 */
		bin_cmd += get_operand_type(inp.op2_type,inp.op2);
		bin_cmd <<= 3;

		/* operand 2 */
		tmp_register = hash_get_key(registers,inp.op2);
		if(tmp_register != NOT_EXIST)
			bin_cmd += tmp_register;
	}
	else
	{
		bin_cmd<<=3;
	}

	/* finish */
	return bin_cmd;
}

/* Add all symbols in data_symbols to the original symbols hash.
 * since the data must be last it will be incresed in IC of its original position */
void reorder_symbols(int IC)
{
	HashNode *i;
	for_each_item(i,data_symbols)
	{
		hash_add_item(symbols,i->key+IC,i->value);
	}
}

/* after the first scan we can now set the real entry label locations
 * the reorder_entry function replaces the right code referances of the labels
 * which declared as entry. also notifies if a label was not found */
void reorder_entry()
{
	HashNode *i;
	for_each_item(i,entry)
	{
		i->key = hash_get_key(symbols,i->value);
		if(i->key == NOT_EXIST)
		{
			/*ERROR: label not exist */
			char* msg = new_string(80);
			sprintf(msg, "Label '%s' not exist for entry", i->value);
			hash_add_item(errors,0,msg);
		}
	}
}

/* The second scan of the commands. It's goals are:
 * 1) To assign the proper referances codes to entry Hash
 * 2) To assign the proper referances codes to extern Hash
 * 3) To combine data symbols with original symbols
 * 4) To encode the commands into binary codes and return those commands
 * 5) also check for errors that the first scan could not know about such
 * 	  as: used label does not exist
 */
Output_Command* second_scan(Input_Command input[],int input_size, int IC,int DC)
{
	/* out_IC is the IC counter to the output command loop */
	int i,out_IC;

	/* the output binary commands which will be returned and printed to file later */
	Output_Command* output = malloc(sizeof(Output_Command)*MEM_SIZE);

	reorder_symbols(IC);
	reorder_entry();

	for(i=0,out_IC=0; i<input_size; i++)
	{
		/* in the second scan, we are interested only in real commands (mov,jmp...) */
		if(hash_get_key(commands,input[i].command)==NOT_EXIST)
			continue;

		/* get the binary command code */
		output[out_IC].bin_command = build_binary_command(input[i]);
		output[out_IC].linker_data = 'a';
		out_IC++;

		/* if op1 is specified, we will add another binary command */
		if(input[i].op1 != NULL && hash_get_key(registers,input[i].op1)==NOT_EXIST)
		{
			/* for regular numbers, the second command will be the number itself */
			if(is_numeric(input[i].op1))
			{
				output[out_IC].bin_command = atoi(input[i].op1);
				output[out_IC].linker_data = 'a';
				out_IC++;
			}
			else
			{
				/* for labels, the second command will be the memory location
				 * of the label */
				int label_code;
				label_code = hash_get_key(symbols,input[i].op1);
				if(label_code!=NOT_EXIST)
				{
					/* if op type is * we will calculate the distance
					 * between current line to the label location */
					if(input[i].op1_type!=NULL && strcmp(input[i].op1_type,"*")==0)
					{
						output[out_IC].bin_command = label_code - out_IC+1;

						output[out_IC].linker_data = 'a';
					}
					else
					{
						output[out_IC].bin_command = label_code;
						output[out_IC].linker_data = 'r';
					}

					out_IC++;
				}
				else
				{
					/* for external labels, the second command will be 0 */
					label_code = hash_get_key(externals,input[i].op1);
					if(label_code!=NOT_EXIST)
					{
						output[out_IC].bin_command = 0;
						output[out_IC].linker_data = 'e';

						/* add to externals table the proper code */
						hash_add_item(externals,out_IC,input[i].op1);

						out_IC++;
					}
					/* label was not found in externals or in symbols = invalid label */
					else
					{
						/*ERROR: label not exist */
						char* msg = new_string(80);
						sprintf(msg, "Label '%s' not exist", input[i].op1);
						hash_add_item(errors,0,msg);
					}
				}
			}
		} /* if op1 != NULL*/

		/* if op2 is specified, we will add another binary command */
		if(input[i].op2 != NULL && hash_get_key(registers,input[i].op2)==NOT_EXIST)
		{
			/* for regular numbers, the second command will be the number itself */
			if(is_numeric(input[i].op2))
			{
				output[out_IC].bin_command = atoi(input[i].op2);
				output[out_IC].linker_data = 'a';
				out_IC++;
			}
			else
			{
				/* for labels, the second command will be the memory location
				 * of the label */
				int label_code;
				label_code = hash_get_key(symbols,input[i].op2);
				if(label_code!=NOT_EXIST)
				{
					/* if op type is * we will calculate the distance
					 * between current line to the label location */
					if(input[i].op2_type!=NULL && strcmp(input[i].op2_type,"*")==0)
					{
						output[out_IC].bin_command = label_code - out_IC+1;

						output[out_IC].linker_data = 'a';
					}
					else
					{
						output[out_IC].bin_command = label_code;
						output[out_IC].linker_data = 'r';
					}

					output[out_IC].linker_data = 'r';
					out_IC++;
				}
				else
				{
					/* for external labels, the second command will be 0 */
					label_code = hash_get_key(externals,input[i].op2);
					if(label_code!=NOT_EXIST)
					{
						output[out_IC].bin_command = 0;
						output[out_IC].linker_data = 'e';

						/* add to externals table the proper code */
						hash_add_item(externals,out_IC,input[i].op1);

						out_IC++;
					}
					/* label was not found in externals or in symbols = invalid label */
					else
					{
						/*ERROR: label not exist */
						char* msg = new_string(80);
						sprintf(msg, "Label '%s' not exist", input[i].op2);
						hash_add_item(errors,0,msg);
					}
				}
			}
		} /* if op2 != NULL*/
	} /* for */

	return output;

} /*second scan */
