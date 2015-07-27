/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : output.h
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : functions to print the output binary data to files
 ============================================================================
 */
#ifndef OUTPUT_H_
#define OUTPUT_H_


/* output the entry hash to a file called xxx.ent.ob
 * xxx is the given filename */
void output_entry(char*file_name);

/* output the externals hash to a file called xxx.ext.ob
 * xxx is the given filename */
void output_extern(char*file_name);

/* output the binary command output from second scan to a text file called xxx.ob
 * xxx is the given file name */
void output_commands(char*file_name,Output_Command* output,int IC,int DC);


/* Does the whole output process. outputs the second scan results -
 *  the entry (if exists), externals (if exists) and the binary commands into .ob files
 */
void output_all(char*file_name,Output_Command* output,int IC,int DC);

#endif /* OUTPUT_H_ */
