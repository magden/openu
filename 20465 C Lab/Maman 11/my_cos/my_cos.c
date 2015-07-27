/*
 ============================================================================
 Name        : my_cos.h
 Author      : Stas Seldin
 Course      : 20465
 Semester    : 2012b
 Description : This program
 ============================================================================
 */

#include <stdio.h>
#include <math.h>
#include "my_cos.h"

long double my_fact(int num)
{
	long double ans=1;
	while(num > 0)
		ans*=(num--);
	return ans;
}

double my_pow(double x,int y)
{
	double ans=1;
	while(y-- > 0)
		ans*=x;
	return ans;
}

double my_abs(double num)
{
	return num>0 ? num : num*(-1);
}

double my_cos (double x)
{
	int i = 1;
	double ans = 0;
	double calc = 1;

	/* cos(x) is same as cos(-x) so we will use the absolute value of x */
	x = my_abs(x);

	/* since cosine is a periodic function, each period of PI*2 is the same */
	/* so we will remove every PI*2 that fits x */
	if(x > PI*2)
		x = x - (int)(x/(PI*2)) *PI*2;

	/* Calculation of cosine to the specified accuracy in the header file */
	while(my_abs(calc) > ACCURACY)
	{
		ans+= calc;
		calc = (1-(i%2)*2) * my_pow(x,2*i) / my_fact(2*i);
		++i;
	}
	return ans;
}

int main(void) {
	double x;

	printf("Enter value for cos:\n");
	while(scanf("%lf",&x))
	{
		printf("my_cos(%lf) = %1.9lf\n",x,my_cos(x));
		printf("cos(%lf) = %1.9lf\n",x,cos(x));
		printf("diff = %1.9lf\n\n",my_abs(my_cos(x)-cos(x)));
		printf("Enter another value for cos:\n");
	}
	return 0;
}
