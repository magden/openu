/*
 * set.h
 *
 *  Created on: May 19, 2012
 *      Author: stas
 *
 *  Dscription:
 */

#ifndef SET_H_
#define SET_H_

#define SET_SIZE 128
#define BITS 8
#define PROMPT "$>"
#define MAX_INPUT_SIZE 200
#define MAX_COMMAND_SIZE 32

typedef char Set_unit;
typedef Set_unit* Set;

Set new_set();
void read_set(Set s,int x);
void print_set(Set s);
Set union_set(Set s1,Set s2);
Set intersect_set(Set s1,Set s2);
Set sub_set(Set s1,Set s2);

#endif /* SET_H_ */
