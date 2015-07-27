/*
 ============================================================================
 Name        : my_scalar.h
 Author      : Stas Seldin
 Course      : 20465
 Semester    : 2012b
 Description : Hello World in C, Ansi-style
 ============================================================================
 */



/*
 * This function calculates the scalar multiplication of 2 vectors of the same size
 * v1 - first vector
 * v2 - second vector
 * size - both size of the vectors
 *
 * assumptions:
 * 1) v1 and v2 must be at the same size
 * 2) the size parameter matches the same size of v1 and v2
 */
int scalar_product(int* v1,int* v2,int size);
