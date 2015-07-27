/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : init.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This is the assembler initialization process. It runs only once
 	 	 	   at the begining of the main program. The main function here is
			   init() which initializes all system hashes
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "hash.h"

/* These are the system hashes. these hashes are widely used in our project  */

/* the first 3 hashes are static hashes that does not change! */
Hash registers; /* The allowed registers and their code */
Hash commands;  /* The defined commands and their code */
Hash operand_types; /* The defined operand types (*,#) and their code */

/* the last 5 hashes are dynamic hashes that the first scan,
 *  the second scan and the output process are using
 *
 *  these hashes cleared after we  finish to process a file
*/
Hash errors; /* keeps all the errors in the whole process (read file, first & second scan) */
Hash symbols; /* keeps all the non-data labels which are defined */
Hash data_symbols; /* keeps all the data labels which are defined. after the first scan. we will move all the items in this hash into symobls hash above */
Hash externals; /* keeps all labels which specified after .extern command */
Hash entry; /* keeps all labels which specified after .entry command */

/* This is the main initialization function.
 * The function allocates memory to all of the global system hashes above
 * The function also fills the system hashes with the relevant data (commands, registers...)
 */
void init()
{
	/* init list of errors */
	errors = new_hash();

	/* init list of registers */
	registers = new_hash();
	hash_add_item(registers,0,"r0");
	hash_add_item(registers,1,"r1");
	hash_add_item(registers,2,"r2");
	hash_add_item(registers,3,"r3");
	hash_add_item(registers,4,"r4");
	hash_add_item(registers,5,"r5");
	hash_add_item(registers,6,"r6");
	hash_add_item(registers,7,"r7");

	/* init list of commands */
	commands = new_hash();
	hash_add_item(commands,0,"mov");
	hash_add_item(commands,1,"cmp");
	hash_add_item(commands,2,"add");
	hash_add_item(commands,3,"sub");
	hash_add_item(commands,4,"ror");
	hash_add_item(commands,5,"shr");
	hash_add_item(commands,6,"lea");
	hash_add_item(commands,7,"inc");
	hash_add_item(commands,8,"dec");
	hash_add_item(commands,9,"jmp");
	hash_add_item(commands,10,"bne");
	hash_add_item(commands,11,"red");
	hash_add_item(commands,12,"prn");
	hash_add_item(commands,13,"jsr");
	hash_add_item(commands,14,"rts");
	hash_add_item(commands,15,"hlt");

	/* init list of commands types */
	operand_types = new_hash();
	hash_add_item(operand_types,0,"#");
	hash_add_item(operand_types,3,"*");

	/* init global hashes */
	symbols = new_hash();
	data_symbols = new_hash();
	externals = new_hash();
	entry = new_hash();
}
