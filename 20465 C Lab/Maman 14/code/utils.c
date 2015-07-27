/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : utils.c
 Author      : Dor Laska (305465775) & Stas Seldin (311950943)
 Semester    : 2012b
 Description : Utility functions which are not related to each other but
 	 	 	   globaly used in the project
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "utils.h"
#include "consts.h"

/* returns true if the specified string is contains
 * only digits or minus sign at the begining */
int is_numeric(const char*str)
{
    if (*str) {
    	 if(*str=='-') /* minus is also allowed */
    		 str++;
         char c;
         while ((c=*str++)) {
               if (!isdigit(c))
            	   return FALSE;
         }
         return TRUE;
     }
     return FALSE;
}

/* allocates membory for specified sized char array (string)
 * returns a pointer to the newly allocated string */
char* new_string(int size)
{
	char*str = (char*)malloc(sizeof(char)*size);
	return str;
}

/* returns ture if the specifed char is an english letter a-z or A-Z */
int is_letter(char c)
{
	return ((c>='A' && c<='Z') || (c>='a' && c<='z'));
}


