/*
 ============================================================================
 ============================= Maman 14 =====================================
 File Name   : hash.h
 Semester    : 2012b
 Description : Defines a new data structure based on key-value linked list
 ============================================================================
 */

#ifndef HASH_H_
#define HASH_H_

/* The data type of the keys */
typedef int KeyType;

/* The data type of the values */
typedef char* ValueType;

/* hashNode struct */
 typedef struct hashNode {
	KeyType key;
	ValueType value;
	struct hashNode* next;
} HashNode;

/* Hash struct */
 typedef struct {
	HashNode* head;
}* Hash;




/* Allocates memory for a new hash and returns the pointer to it*/
Hash new_hash();

/* Returns true if the given Hash is empty */
int hash_empty(Hash);

/* Returns the number of total elements in the Hash */
int hash_length(Hash);

/* Prints the Hash */
void hash_print(Hash);

/* Adds new item to the hash */
void hash_add_item(Hash, KeyType, ValueType);

/* Searches for an item by its key and removes it from the hash */
void hash_remove_item(Hash, KeyType);

/* removes all items from the hash */
void hash_clear(Hash);

/* Search value in the hash by key */
ValueType hash_get_value(Hash, KeyType);

/* Search key in the hash by value */
KeyType hash_get_key(Hash, ValueType);

/* a macro to loop all the items in the list */
#define for_each_item(item, hash) \
    for(item = hash->head; item != NULL; item = item->next)

/*  
	comparison function between 2 keys
	return true if both keys equal
 */
int hash_key_cmp(KeyType, KeyType);


/*  
	comparison function between 2 values
	return true if both keys equal
 */
int hash_value_cmp(ValueType, ValueType);

#endif /* HASH_H_ */
