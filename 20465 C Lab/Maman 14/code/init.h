/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : init.h
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This is the assembler initialization process. It runs only once
 	 	 	   at the begining of the main program. The main function here is
			   init() which initializes all system hashes
 ============================================================================
 */

#ifndef INIT_H_
#define INIT_H_

/* Input_Command struct holds in it the whole assembly
   program after parsing it from text file */
typedef struct {
	int line;
	char* label;
	char* command;
	char* op1_type;
	char* op1;
	char* op2_type;
	char* op2;
	int*  data;
	int   data_size;
	char* string;
	char* entry;
}Input_Command;

/* Output_Command struct holds the binary encoded program
 *  which is ready to be printed in the output file*/
typedef struct {
	int bin_command;
	char linker_data; /* 'a','e' or 'r' */
}Output_Command;


/* This is the main initialization function.
 * The function allocates memory to all of the global system hashes above
 * The function also fills the system hashes with the relevant data (commands, registers...)
 */
void init();

#endif /* INIT_H_ */
