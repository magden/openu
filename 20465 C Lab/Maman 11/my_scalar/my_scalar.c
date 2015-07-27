/*
 ============================================================================
 Name        : my_scalar.h
 Author      : Stas Seldin
 Course      : 20465
 Semester    : 2012b
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include<stdio.h>

int scalar_product(int* v1,int* v2,int size)
{
	int result=0;
	while(size-- >0)
		result+= (*v1++)*(*v2++);

	return result;
}

/* read from the user a positive integer number */
int getSize(){
	int size;
	printf("Please enter size:");

	/* show error while the input is not a number or a negative number */
	for( ;scanf("%d",&size)!=1 || size<0;scanf("%*s"))
		printf("\nError: Please enter size:");

	printf("\n");
	return size;
}

/*
 * read from the user a list of number and insert them in order to array x
 * Assumption: x has enough memory to contain the numbers
 */
void readVec(int* x,int size){
	int i;
	for(i=0; i<size;i++){
		printf("\nPlease enter number %d:",i+1);

		/* show error while the input is not a number */
		for( ;scanf("%d",&x[i])!=1;scanf("%*s"))
			printf("\nError: Please enter member %d:",i+1);
	}
	printf("\n");
}


int main()
{
	int size=getSize();
	int u[size], v[size];
	printf("\nNow entering numbers for first vector:\n");
	readVec(u,size);
	printf("\nNow entering numbers for second vector:\n");
	readVec(v,size);
	printf("\nResult = %d\n\n",scalar_product(v,u,size));
	return 0;
}
