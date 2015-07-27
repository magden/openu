/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : firstScan.h
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This is the first scan process. The goal of the first scan
 	 	 	   is to count the binary program length, collect all declared
 	 	 	   labels and data AND to catch various types of errors
 ============================================================================
*/

#ifndef FIRSTSCAN_H_
#define FIRSTSCAN_H_

/* returns true if the given labbel name is legal */
int is_legal_label(char *label);

/* this function returns the number of operands the command uses */
int cmd_opers_num(char*);

/* get operand type code.
 * return NOT_EXIST if invalid operand type - operand combination*/
int get_operand_type(char*,char*);

/* translates the command to binary machine code*/
int build_binary_command(Input_Command);

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
void first_scan(Input_Command* input,int input_size, int*IC,int*DC);

#endif /* FIRSTSCAN_H_ */
