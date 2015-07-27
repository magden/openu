/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : utils.h
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : Utility functions which are not related to each other but
 	 	 	   globaly used in the project
 ============================================================================
 */

#ifndef UTILS_H_
#define UTILS_H_

/* This macro helps to print negative decimal numbers to octal numbers*/
/* it generally removes the first 16 bits in a 32 bit integer
 * ex: converts: 1111 1111 1111 1111 1011 1110 1011 0011
 * 		into:    0000 0000 0000 0000 1011 1110 1011 0011
 *
 * we used to so negative numbers will be printer well with printf %o
 */
#define to16bit(n) (n)>=0? n : ((n)^-65536)


/* returns true if the specified string is contains
 * only digits or minus sign at the begining */
int is_numeric(const char*);

/* allocates membory for specified sized char array (string)
 * returns a pointer to the newly allocated string */
char* new_string(int);

/* returns ture if the specifed char is an english letter a-z or A-Z */
int is_letter(char c);
#endif /* UTILS_H_ */
