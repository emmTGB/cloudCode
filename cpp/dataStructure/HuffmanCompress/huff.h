#pragma once

#ifndef HUFF_H
#define HUFF_H

#define OK 1
#define ERR 0
#define inf 0x7fffffff

typedef struct {
	int weight;
	int parent;
	int lchild;
	int rchild;
}HTNode, *HuffmanTree;

typedef char** HuffmanCode;

int createHuffmanTree(HuffmanTree pHT, int weight[], int n);
int createHuffManTree__(HuffmanTree& pHT, int weight[], int n);
int huffmanCoding(HuffmanCode& pHC, HuffmanTree& pHT);

int select(HuffmanTree pHT, int nSize);
void select(HuffmanTree& pHT, int i, int& s1, int& s2);

int testHuffmanTree(HuffmanTree pHT);
void testHuffmanCode(HuffmanCode& pHC);

#endif