/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : hash.c
 Semester    : 2012b
 Description : Defines a new data structure based on key-value linked list
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hash.h"
#include "consts.h"

/* Allocates memory for a new hash and returns the pointer to it*/
Hash new_hash()
{
	Hash h = (Hash)malloc(sizeof(Hash));
	return h;
}

/* Returns true if the given Hash is empty */
int hash_empty(Hash hash)
{
	return (hash->head == NULL);
}

/* Returns the number of total elements in the Hash */
int hash_length(Hash hash)
{
	HashNode *p = hash->head;
	int length = 0;
	while(p != NULL)
	{
		length++;
		p = p->next;
	}
	return length;
}

/* Prints the Hash */
void hash_print(Hash hash)
{
	if(hash_empty(hash))
	{
		printf("(empty hash)\n");
	}
	else
	{
		HashNode *p = hash->head;
		while(p!=NULL)
		{
			printf("(%d = %s) -> ",p->key,p->value);
			p = p->next;
		}
		printf("//\n");
	}
}

/* Adds new item to the hash */
void hash_add_item(Hash hash, KeyType itemKey, ValueType itemValue)
{
	/* Create new item */
	HashNode *newNode = malloc(sizeof(HashNode));
	newNode->key = itemKey;
	newNode->value = itemValue;
	newNode->next = NULL;
	
	/* if hash empty insert into head */
	if(hash_empty(hash))
	{
		hash->head = newNode;
	}
	
	/* Insert new item in the end of the list */
	else
	{
		HashNode *p = hash->head;
		HashNode *prev = NULL;
		
		/* scan all list until the last item */
		while(p!=NULL)
		{
			prev = p;
			p = p->next;
		}
		
		p = newNode;
		prev->next = p;
	}
}

/* Searches for an item by its key and removes it from the hash */
void hash_remove_item(Hash hash, KeyType key)
{
	HashNode *p = hash->head;
	HashNode *prev = NULL;
	
	while(p!=NULL)
	{
		if(hash_key_cmp(key,p->key))
		{
			/* if we need to delete the first node */
			if(prev==NULL)
				hash->head = p->next;
			else
				prev->next = p->next;
			free(p);
			return;
		}
		
		prev = p;
		p = p->next;
	}
}


/* Search value in the hash by key */
ValueType hash_get_value(Hash hash,KeyType key)
{
	HashNode *p = hash->head;
	while(p!=NULL)
	{
		if(hash_key_cmp(key, p->key))
			return p->value;
		p = p->next;
	}
	return NULL;
}

/* Search key in the hash by value */
KeyType hash_get_key(Hash hash,ValueType value)
{
	HashNode *p = hash->head;
	while(p!=NULL)
	{
		if(hash_value_cmp(p->value, value))
			return p->key;
		p = p->next;
	}
	return (KeyType)NOT_EXIST;
}

/* removes all items from the hash */
void hash_clear(Hash hash)
{
	HashNode *p,*to_delete;
	p = to_delete = hash->head;
	while(p!=NULL)
	{
		p=p->next;
		free(to_delete);
		to_delete = p;
	}
	hash->head = NULL;
}
/*  
	comparison function between 2 keys
	return true if both keys equal
 */
int hash_key_cmp(KeyType k1, KeyType k2)
{
	return (k1==k2);
}

/*  
	comparison function between 2 values
	return true if both keys equal
 */
int hash_value_cmp(ValueType v1, ValueType v2)
{
	return (strcmp(v1,v2)==0);
}




