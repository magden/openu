/*
 * set.c
 *
 *  Created on: May 19, 2012
 *      Author: stas
 */
#include "set.h"
#include <stdio.h>
#include <stdlib.h>

static const int unit_size = sizeof(Set_unit)*BITS;

Set new_set()
{
	int const size = SET_SIZE / unit_size;
	Set s = malloc(size);
	int i;
	for(i=0;i<size;i++)
		s[i]=0;
	
	return s;
}

void read_set(Set s,int num)
{
	s[num/unit_size] = s[num/unit_size] | (1<<num%unit_size);
}

void print_set(Set s)
{
	int const size = SET_SIZE / unit_size;
	int i,j;
	for(i=0; i<size ;i++)
	{
		Set_unit tmp = s[i];
		for(j=0; j<unit_size; j++)
		{
			if(tmp%2!=0)
				printf("%d ",i*unit_size+j);
			tmp/=2;
		}
	}
	printf("\n");
}

Set union_set(Set s1,Set s2)
{
	int i;
	Set result = new_set();
	for(i=0;i < SET_SIZE / unit_size;i++)
		result[i] = s1[i] | s2[i];
	return result;
}

Set intersect_set(Set s1,Set s2)
{
	int i;
	Set result = new_set();
	for(i=0;i < SET_SIZE / unit_size;i++)
		result[i] = s1[i] & s2[i];
	return result;
}

Set sub_set(Set s1,Set s2)
{
	int i;
	Set result = new_set();
	for(i=0;i < SET_SIZE / unit_size;i++)
		result[i] = s1[i] & (~s2[i]);
	return result;
}
