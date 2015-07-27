/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : output.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This converts the raw script files to input_command struct
 ============================================================================
 */

#ifndef READ_FILE_H_
#define READ_FILE_H_

/* the function get a char * type which
 * is a line of a file and an Input_Command which
 * is a structure of commands defined in init.h file
 * parse the line by splitting it into words and put
 * each word in a structure
 */
int work_with_str(char * line,Input_Command *command);

/*parse the line by spliting it into words and put
 * each word in a structure
 */
Input_Command* read_file(char* filename,int*command_count);


#endif /* READ_FILE_H_ */
