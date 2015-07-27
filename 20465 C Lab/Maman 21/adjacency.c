/*
 * adjacency.c
 *
 *	Maman 21 (2012b)
 *  Created on: Apr 28, 2012
 *  Author: stas Seldin (id 311950943)
 *
 *	This file defines a NxN sized matrix which represents a tree
 *	tree[v][u] == 1 only if there is a path from v to u
 */

#include<stdio.h>
#define N 11
typedef int adj_mat[N][N];

enum BOOL{
	FALSE=0,
	TRUE=1
};

/*
 * This function reads values into a NxN sized matrix which represents a tree
 * Assumption: user has entered a valid input
 */
void read_mat(adj_mat mat)
{
	int i,j;
	for(i=0;i<N;i++)
	{
		for(j=0;j<N;j++)
		{
			printf("Is there a path from %d to %d? [enter 0 or 1] ",i+1,j+1);
			scanf("%d",&mat[i][j]);
		}
	}
}
/*
 * This functions prints a NxN sized matrix which represents a tree
 */
void print_mat(adj_mat mat)
{
	int i,j;
	printf("\n");
	for(i=0;i<N;i++)
	{
		for(j=0;j<N;j++)
			printf("%d ",mat[i][j]);
		printf("\n");
	}
}

/*
 * This function checks if there is a path from u to v in a given tree A
 */
int path(adj_mat A,int u,int v)
{
	if(A[u][v] || u==v)
		return TRUE;
	else
	{
		int i;
		for(i=0;i<N;i++)
		{
			if(A[u][i] && path(A,i,v))
				return TRUE;
		}
	}
	return FALSE;
}

int main()
{
	int from,to;
	adj_mat tree;

	read_mat(tree);
	print_mat(tree);

	printf("\nEnter from path: ");
	scanf("%d",&from);
	printf("Enter to path: ");
	scanf("%d",&to);

	if(path(tree,from-1,to-1))
		printf("\nThere is a path from %d to %d\n",from,to);
	else
		printf("\nThere is no a path from %d to %d\n",from,to);

	return 0;
}
