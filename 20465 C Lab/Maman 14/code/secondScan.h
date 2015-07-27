/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : secondScan.h
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : This is the second scan process. Its job is to check that all
 	 	 	   labels are exist, set the proper code referances to entry and
 	 	 	   entern hashes, combine symbols and data_symbols hashes
 	 	 	   and create the binary commands to output
 ============================================================================
 */

#ifndef SECONDSCAN_H_
#define SECONDSCAN_H_


/* translates the command to binary machine code*/
int build_binary_command(Input_Command);

/* Add all symbols in data_symbols to the original symbols hash.
 * since the data must be last it will be incresed in IC of its original position */
void reorder_symbols(int IC);

/* after the first scan we can now set the real entry label locations
 * the reorder_entry function replaces the right code referances of the labels
 * which declared as entry. also notifies if a label was not found */
void reorder_entry();


/* The second scan of the commands. It's goals are:
 * 1) To assign the proper referances codes to entry Hash
 * 2) To assign the proper referances codes to extern Hash
 * 3) To combine data symbols with original symbols
 * 4) To encode the commands into binary codes and return those commands
 * 5) also check for errors that the first scan could not know about such
 * 	  as: used label does not exist
 */
Output_Command* second_scan(Input_Command input[],int input_size, int IC,int DC);

#endif /* SECONDSCAN_H_ */
