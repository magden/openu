/*
 ============================================================================
 Name        : my_cos.h
 Author      : Stas Seldin
 Course      : 20465
 Semester    : 2012b
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

/* The accuracy to use in my_cos function */
#define ACCURACY 0.000001

/* This is a part of PI number */
#define PI 3.14159265358979

/* my_fact calculates and returns the factorial of num */
long double my_fact(int num);

/* my_pow calculates and returns the y-th power of x (x^y) */
double my_pow (double x,int y);

/* my_abs calculates and returns the absolute number of num */
double my_abs(double num);

/* my_cos calculates and returns the cosine function on x with a specified accuracy */
double my_cos (double x);
