#include "huff.h"

#include<iostream>
#include<string>
#include <string.h>



int createHuffmanTree(HuffmanTree pHT, int weight[], int n)
{
	int s1, s2, i;
	int m = 2 * n - 1;

	//��ʼ��Huffman��
	for (i = 1; i <= n; i++) {
		pHT[i].weight = weight[i - 1];
		pHT[i].lchild = 0;
		pHT[i].rchild = 0;
		pHT[i].parent = 0;
	}

	for (i = n + 1; i <= m; i++) {
		pHT[i].weight = 0;
		pHT[i].lchild = 0;
		pHT[i].rchild = 0;
		pHT[i].parent = 0;
	}


	for (i = n + 1; i <= m; i++) {
		select(pHT, i - 1, s1, s2);
		pHT[s1].parent = i;
		pHT[s2].parent = i;

		pHT[i].lchild = s1;
		pHT[i].rchild = s2;

		pHT[i].weight = pHT[s1].weight + pHT[s2].weight;
	}

	return OK;
}

int createHuffManTree__(HuffmanTree& pHT, int weight[], int n)
{
	if (n <= 1) return ERR;
	pHT = new HTNode[2 * n];

	return createHuffmanTree(pHT, weight, n);
}

int huffmanCoding(HuffmanCode& pHC, HuffmanTree& pHT)
{
	char cd[256] = { 0 };
	int cdLen = 0;

	for (int i = 1; i < 512; i++) {
		pHT[i].weight = 0;
	}

	int p = 511;
	while (p != 0) {
		if (pHT[p].weight == 0) {
			pHT[p].weight = 1;
			if (pHT[p].lchild != 0) {
				p = pHT[p].lchild;
				cd[cdLen++] = '0';
			}
			else if (pHT[p].rchild == 0) {
				pHC[p] = new char[cdLen + 1];
				cd[cdLen] = 0;
				strcpy(pHC[p], cd);
			}
		}
		else if (pHT[p].weight == 1) {
			pHT[p].weight = 2;
			if (pHT[p].rchild != 0) {
				p = pHT[p].rchild;
				cd[cdLen++] = '1';
			}
		}
		else {
			pHT[p].weight = 0;
			p = pHT[p].parent;
			cdLen--;
		}
	}

	return OK;
}

int select(HuffmanTree pHT, int nSize)
{
	int minValue = inf;
	int minPos = 0;

	for (int i = 1; i <= nSize; i++) {
		if (pHT[i].parent == 0 && pHT[i].weight < minValue) {
			minValue = pHT[i].weight;
			minPos = i;
		}
	}

	return minPos;
}

void select(HuffmanTree& pHT, int i, int& s1, int& s2)
{
	int minValue = inf;

	for (int j = 1; j <= i; j++) {
		if (pHT[j].parent == 0 && pHT[j].weight < minValue) {
			minValue = pHT[j].weight;
			s1 = j;
		}
	}

	minValue = inf;
	for (int j = 1; j <= i; j++) {
		if (j != s1 && pHT[j].parent == 0 && pHT[j].weight < minValue) {
			minValue = pHT[j].weight;
			s2 = j;
		}
	}
}


