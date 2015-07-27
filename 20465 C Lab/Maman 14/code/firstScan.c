/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : firstScan.c
 Semester    : 2012b
 Description : This is the first scan process. The goal of the first scan
 	 	 	   is to count the binary program length, collect all declared
 	 	 	   labels and data AND to catch various types of errors
 ============================================================================
*/
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include "init.h"
#include "hash.h"
#include "consts.h"
#include "utils.h"

extern Hash errors;
extern Hash commands;
extern Hash registers;
extern Hash operand_types;
extern Hash symbols;
extern Hash data_symbols;
extern Hash externals;
extern Hash entry;

int data[MEM_SIZE];

/* returns true if the given labbel name is legal */
int is_legal_label(char *label)
{
	/* label must be not null */
	if(label == NULL)
		return FALSE;

	/* label cannot be the same name as register or command */
	if(hash_get_key(registers,label)!=NOT_EXIST || hash_get_key(commands,label)!=NOT_EXIST)
		return FALSE;

	/* first char must be A-Z or a-z */
	if(!is_letter(label[0]))
		return FALSE;

	int i,len;
	for(i=1,len = strlen(label); i<len; i++)
	{
		/* every char of the label can be only digit or letter */
		if(!isdigit(label[i]) && !is_letter(label[i]))
			return FALSE;
	}
	return TRUE;
}

/* this function returns the number of operands the command uses */
int cmd_opers_num(char * cmd)
{
	int cmd_num = hash_get_key(commands,cmd);
	if(cmd_num < 7)
		return 2;

	if(cmd_num < 14)
		return 1;

	return 0;
}

/* get operand type code.
 * return NOT_EXIST if invalid operand type - operand combination*/
int get_operand_type(char*op_type,char*op)
{
	if(op_type==NULL)
	{
		/* can be register or label*/
		if(hash_get_key(registers,op)==NOT_EXIST)
		{
			if(is_legal_label(op)==TRUE)
				return OP_DIRECT;/* label */
			else
				return INVALID; /* illegal label name */
		}
		return OP_DIRECT_REGISTER;/* regsiter */
	}

	else if(strcmp(op_type,"*")==0)
	{
		if(hash_get_key(registers,op)==NOT_EXIST)
		{
			if(is_legal_label(op)==TRUE)
				return OP_RELATIVE; /*label */
			else
				return INVALID; /* illegal label name */
		}
		return INVALID; /* because register cannot be with * type */
	}

	else if(strcmp(op_type,"#")==0)
	{
		if(is_numeric(op))
			return OP_IMMDIATE; /* #integer */
		return INVALID; /* because only number can be with # type */

	}

	else /* invalid operand type */
	{
		return INVALID;
	}
}


/* checks if the operand type is allowed to use with specified command */
/* op_position: SOURCE or DESTINATION */
int check_operand_for_command(char*cmd,int op_type,int op_position)
{
	/* lea source operand must be only direct */
	if(op_position == SOURCE && strcmp(cmd,"lea")==0 && op_type != OP_DIRECT)
	{
		return INVALID;
	}

	else if(op_position == DESTINATION)
	{
		/* only cmp & prn destination operand can be immdiate */
		if(op_type==OP_IMMDIATE && strcmp(cmd,"cmp")!=0 && strcmp(cmd,"prn")!=0)
			return INVALID;

		/* jmp, bne & jsr destination operand cannot be register */
		if(op_type == OP_DIRECT_REGISTER &&
		  (strcmp(cmd,"jmp")==0 || strcmp(cmd,"bne")==0 ||strcmp(cmd,"jsr")==0)
		)
			return INVALID;
	}

	return VALID;
}

/* the actual first scan. the operation scans through the input array
 * the first scan does:
 * 1) Counts and sets the IC & DC (data counter)
 * 2) inserts all command labels into symbols hash
 * 3) inserts all data labels into data_symbols hash
 * 4) inserts all entry referances to the entry hash
 * 5) inserts all extern referances to externals hash
 * 6) inserts all data / string into the global data array
 * 7) finds a lot of errors on the way such as: incorect command, missing data,
 * 	  incorect operands for command...
 *
 * At the end of the first scan, it sets the IC and DC to the proper numbers
 * and lets the second scan use those 2 numbers and all of the hashes it filled
 */
void first_scan(Input_Command input[],int input_size, int*IC,int*DC)
{
	int i,j,len;
	for(i = 0; i < input_size; i++)
	{
		/* first, check symbol label */
		if(input[i].label!=NULL)
		{
			/* check if valid label */
			if(is_legal_label(input[i].label) == FALSE)
			{
				/*ERROR: invalid label */
				char* msg = new_string(80);
				sprintf(msg, "Invalid label name '%s' line %d", input[i].label,input[i].line);
				hash_add_item(errors,i,msg);
			}

			/* if label already defined. */
			if(hash_get_key(symbols,input[i].label)!=NOT_EXIST)
			{
				/*ERROR: label already exists */
				char* msg = new_string(80);
				sprintf(msg, "Label '%s' already exists on line %d", input[i].label,input[i].line);
				hash_add_item(errors,i,msg);
			}
		}
		/*
		 * if this is a guidance command (.data .entry .string or .extern)
		 */
		if(input[i].command[0] == '.')
		{
			/* .entry */
			if(strcmp(input[i].command,".entry") == 0)
			{
				/* check for target label */
				if(input[i].entry==NULL)
				{
					/*ERROR: no label */
					char* msg = new_string(80);
					sprintf(msg, "No label found for '.entry' on line %d", input[i].line);
					hash_add_item(errors,i,msg);
					continue;
				}

				/* check if valid label */
				if(is_legal_label(input[i].entry) == FALSE)
				{
					/*ERROR: invalid label */
					char* msg = new_string(80);
					sprintf(msg, "Invalid label name '%s' line %d", input[i].entry,input[i].line);
					hash_add_item(errors,i,msg);
				}

				/* add entry */
				hash_add_item(entry,0,input[i].entry);
			}
			/* .extern */
			else if(strcmp(input[i].command,".extern") == 0)
			{
				/* check for target label */
				if(input[i].entry==NULL)
				{
					/*ERROR: no label */
					char* msg = new_string(80);
					sprintf(msg, "No label found for .extern on line %d", input[i].line);
					hash_add_item(errors,i,msg);
					continue;
				}

				/* check if valid label */
				if(is_legal_label(input[i].entry) == FALSE)
				{
					/*ERROR: invalid label */
					char* msg = new_string(80);
					sprintf(msg, "Invalid label name '%s' line %d", input[i].entry,input[i].line);
					hash_add_item(errors,i,msg);
				}

				/* add extern */
				hash_add_item(externals,NOT_SET,input[i].entry);
			}
			/* .data */
			else if(strcmp(input[i].command,".data") == 0)
			{
				/* check for target data */
				if(input[i].data_size==0)
				{
					/*ERROR: no data */
					char* msg = new_string(80);
					sprintf(msg, "No integer found for '.data' on line %d", input[i].line);
					hash_add_item(errors,i,msg);
					continue;
				}

				/* if Symbol label exists add it to symbols hash */
				if(input[i].label!=NULL)
					hash_add_item(data_symbols,*DC,input[i].label);

				/* add data */
				for(j = 0; j< input[i].data_size;j++,(*DC)++)
					data[*DC] = input[i].data[j];

			}
			/* .string */
			else if(strcmp(input[i].command,".string") == 0)
			{
				/* check for target string */
				if(input[i].string==NULL)
				{
					/*ERROR: no string */
					char* msg = new_string(80);
					sprintf(msg, "No string found for '.string' on line %d", input[i].line);
					hash_add_item(errors,i,msg);
					continue;
				}

				/* if Symbol label exists add it to symbols hash */
				if(input[i].label!=NULL)
					hash_add_item(data_symbols,*DC,input[i].label);

				/* add string */
				len = strlen(input[i].string);
				for(j = 0; j<len;j++,(*DC)++)
					data[*DC] = input[i].string[j];
				data[(*DC)++] = 0;
			}
			/* invalid command */
			else
			{
				/*ERROR: bad command */
				char* msg = new_string(80);
				sprintf(msg, "Invalid command '%s' on line %d", input[i].command, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}
		}

		/*
		 * else this is an action command (mov,lea,jmp...)
		 */
		else if(input[0].command!=NULL)
		{
			/* if Symbol label exists add it to symbols hash */
			if(input[i].label!=NULL)
				hash_add_item(symbols,*IC,input[i].label);

			/* check if invalid command */
			int cmd_num = hash_get_key(commands,input[i].command);
			if(cmd_num == NOT_EXIST)
			{
				/*ERROR: bad command */
				char* msg = new_string(80);
				sprintf(msg, "Invalid command '%s' on line %d", input[i].command, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}

			/* check if there are operand types without operands */
			if(input[i].op1_type != NULL && input[i].op1 == NULL)
			{
				/*ERROR: missing operand 1 */
				char* msg = new_string(80);
				sprintf(msg, "Missing operand after '%s' on line %d", input[i].op1_type, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}

			if(input[i].op2_type != NULL && input[i].op2 == NULL)
			{
				/*ERROR: missing operand 2 */
				char* msg = new_string(80);
				sprintf(msg, "Missing operand after '%s' on line %d", input[i].op2_type, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}

			/* check if user gave the right amount of operands to the command */
			int need_opers = cmd_opers_num(input[i].command);
			int actual_opers = 0;

			if(input[i].op1!=NULL)
				actual_opers++;
			if(input[i].op2!=NULL)
				actual_opers++;

			/* check for the right amount of operands */
			if(actual_opers < need_opers)
			{
				/*ERROR: too few operands */
				char* msg = new_string(80);
				sprintf(msg, "Not enough operands for command '%s' on line %d", input[i].command, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}
			else if(actual_opers > need_opers)
			{
				/*ERROR: too many operands */
				char* msg = new_string(80);
				sprintf(msg, "Too many operands for command '%s' on line %d", input[i].command, input[i].line);
				hash_add_item(errors,i,msg);
				continue;
			}
			else
			{
				/* check operand 1 - type - command combination */
				if(input[i].op1!=NULL)
				{
					int op1_type = get_operand_type(input[i].op1_type,input[i].op1);
					int op1_position = input[i].op2==NULL ? DESTINATION : SOURCE;
					/* check if operands are legal for their type */
					if(op1_type == INVALID)
					{
						/*ERROR: invalid operand type */
						char* msg = new_string(80);
						sprintf(msg, "Invalid operand type for '%s' on line %d", input[i].op1, input[i].line);
						hash_add_item(errors,i,msg);
						continue;
					}

					if(check_operand_for_command(input[i].command,op1_type,op1_position) == INVALID)
					{
						/*ERROR: invalid operand type for command */
						char* msg = new_string(80);
						sprintf(msg, "Invalid source operand type is not allowed to use with command %s on line %d", input[i].command, input[i].line);
						hash_add_item(errors,i,msg);
						continue;
					}
				}

				/* check operand 2 - type - command combination */
				if(input[i].op2!=NULL)
				{
					int op2_type = get_operand_type(input[i].op2_type,input[i].op2);
					/* check if operands are legal for their type */
					if(op2_type == INVALID)
					{
						/*ERROR: invalid operand type */
						char* msg = new_string(80);
						sprintf(msg, "Invalid operand type for '%s' on line %d", input[i].op2, input[i].line);
						hash_add_item(errors,i,msg);
						continue;
					}

					if(check_operand_for_command(input[i].command,op2_type,DESTINATION) == INVALID)
					{
						/*ERROR: invalid operand type for command */
						char* msg = new_string(80);
						sprintf(msg, "Invalid destination operand type is not allowed to use with command %s on line %d", input[i].command, input[i].line);
						hash_add_item(errors,i,msg);
						continue;
					}
				}

				/* increase IC acording to number of binary commands needed */
				(*IC)++; /* first for the command */
				if(input[i].op1 != NULL && hash_get_key(registers,input[i].op1)==NOT_EXIST)
					(*IC)++;
				if(input[i].op2 != NULL && hash_get_key(registers,input[i].op2)==NOT_EXIST)
					(*IC)++;
			}
		}
		/* command is NULL */
		else
		{
			char* msg = new_string(80);
			sprintf(msg, "Command does not exist on line %d", input[i].line);
			hash_add_item(errors,i,msg);
		}
	}
}
