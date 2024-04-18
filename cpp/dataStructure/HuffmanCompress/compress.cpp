#include "compress.h"
#include"huff.h"

#include<iostream>
#include<string>
#include <string.h>

const int SIZE = 256;

int compress(const char* pFilename) {
	/**************************************************/
	//�򿪲�ɨ���ļ�
	std::cout << "开始读取！" << std::endl;
	int weight[256] = { 0 };  //���ļ�����ȡȨ��
	FILE* in = fopen(pFilename, "rb");
	int tempch;
	while ((tempch = getc(in)) != EOF) {
		weight[tempch]++;
	}
	//����ɨ��������ʾ256���ֽڳ��ֵĴ���
	//if (TestWeight(weight))
	//	cout << "������ϣ�����" << endl << endl;

	std::cout << "读取完成！\n" << std::endl;
	fclose(in);

	/**************************************************/
	//����������Huffman��
	int i;
	int n = 256;						//Huffman������n��Ҷ�ӽڵ�
	int m = 2 * n - 1;					//��ô����2n+1���ڵ�

	HuffmanTree pHT = new HTNode[m + 1];	//����Huffman��
	createHuffmanTree(pHT, weight, n);

	//	cout << "�������ɵ�Huffman��" << endl;
	//	if (TestHufTree(pHT))
	//		cout << "������ϣ�" << endl;

		//����Huffman����
	char** pHC = new char* [n + 1]; //����
	for (int i = 1; i <= n; i++)
		pHT[i].weight = weight[i - 1];

	//	cout << "�������ɵ�Huffman��" << endl;
		//�������ɵ�Huffman��
		//cout << "����Huffman�����Թ��̣�\n";
		//if (TestHufTree(pHT))
		//	cout << "������ϣ�" << endl;


	huffmanCoding(pHC, pHT);

	//cout << "���ԣ���ʾ�ֽڵ�Huffman����" << endl;
	//cout << "Byte\tHuffmanCode" << endl;
	//TestHufCode(511, pHT, pHC);

	/**************************************************/

	//������뻺������С
	int nSize = 0;
	for (int i = 0; i < 256; i++)
		nSize += weight[i] * strlen(pHC[i + 1]);
	nSize = (nSize % 8) ? nSize / 8 + 1 : nSize / 8;


	//�Ա����ļ�����ѹ��
	char* pBuffer = NULL;
	pBuffer = new char[nSize];
	memset(pBuffer, 0, (nSize) * sizeof(char));
	encode(pFilename, pHC, pBuffer, nSize);
	if (!pBuffer) {
		return ERR;
	}

	HEAD sHead;
	initHead(pFilename, sHead);
	
	std::cout << "target size:" << sHead.length << "byte" << std::endl;
	int afterLen = writeFile(pFilename, sHead, pBuffer, nSize);
	std::cout << "result size:" << afterLen << "byte" << std::endl;
	std::cout << "compression ratio:" << (double)afterLen * 100 / sHead.length << "%" << std::endl;
	

	delete pHT; delete[] pHC; delete pBuffer;

	return OK;
}

//int compress(const char* pFilename) {
//	std::cout << "loading file..." << std::endl;
//
//	int weight[256] = { 0 };
//	FILE* in = fopen(pFilename, "rb");
//	int tempch;
//	while ((tempch = getc(in)) != EOF) {
//		weight[tempch]++;
//	}
//
//	std::cout << "fin!" << std::endl;
//	fclose(in);
//
//	int i;
//	int n = 256;
//	int m = 2 * n - 1;
//
//	HuffmanTree pHT = new HTNode[m - 1];
//	createHuffmanTree(pHT, weight, n);
//
//	char** pHC = new char* [n + 1];
//	for (int i = 1; i <= n; i++) {
//		pHT[i].weight = weight[i - 1];
//	}
//
//	huffmanCoding(pHC, pHT);
//
//	int nSize = 0;
//	for (int i = 0; i < 256; i++) {
//		nSize += weight[i] * strlen(pHC[i]);
//	}
//	nSize = (nSize % 8) ? nSize / 8 + 1 : nSize / 8;
//
//	char* pBuffer = new char[nSize];
//	memset(pBuffer, 0, (nSize) * sizeof(char));
//	encode(pFilename, pHC, pBuffer, nSize);
//	if (!pBuffer) {
//		return ERR;
//	}
//
//	HEAD sHead;
//	initHead(pFilename, sHead);
//	std::cout << "target size:" << sHead.length << "byte" << std::endl;
//	int afterLen = writeFile(pFilename, sHead, pBuffer, nSize);
//	std::cout << "result size:" << afterLen << "byte" << std::endl;
//	std::cout << "compression ratio:" << (double)afterLen * 100 / sHead.length << "%" << std::endl;
//
//	delete[] pHT;
//	delete[] pHC;
//	delete[] pBuffer;
//
//	return OK;
//}

int initHead(const char* pFilename, HEAD& sHead) {
	strcpy(sHead.type, "HUF");
	sHead.length = 0;
	for (int i = 0; i < SIZE; i++) {
		sHead.weight[i] = 0;
	}

	FILE* in = fopen(pFilename, "rb");

	int ch;
	while ((ch = fgetc(in)) != EOF) {
		sHead.weight[ch]++;
		sHead.length++;
	}

	fclose(in);
	in = NULL;

	return 0;
}

//int encode(const char* pFilename, char** pHC, char* pBuffer, const int nSize) {
//	
//	FILE* in = fopen(pFilename, "rb");
//
//	pBuffer = new char[nSize];
//
//	if (!pBuffer) {
//		std::cout << "failed to open up" << std::endl;
//	}
//
//	char cd[SIZE] = { 0 };
//	int pos = 0;
//	int ch;
//
// 
//	while ((ch = fgetc(in) != EOF)) {
//		strcat(cd, pHC[ch + 1]);
//
//		while (strlen(cd) >= 8 && pos < nSize) {
//			pBuffer[pos++] = strToByte(cd);
//			for (int i = 0; i < SIZE - 8; i++) {
//				cd[i] = cd[i + 8];
//			}
//		}
//	}
//
//	if (strlen(cd) > 0 && pos < nSize) {
//		pBuffer[pos++] = strToByte(cd);
//	}
//	fclose(in);
//
//	return OK;
// }


int encode(const char* pFilname, const HuffmanCode pHC, char* pBuffer, const int nSize) {
	FILE* in = fopen(pFilname, "rb");

	pBuffer = (char*)malloc(nSize * sizeof(char));
	if (!pBuffer) {
		std::cout << "开辟失败" << std::endl;
	}

	char cd[SIZE] = { 0 };
	int pos = 0;
	int ch;
	
	while ((ch = fgetc(in)) != EOF) {
		strcat(cd, pHC[ch + 1]);

		while (strlen(cd) >= 8) {
			pBuffer[pos++] = strToByte(cd);
			for (int i = 0; i < SIZE - 8; i++) {
				cd[i] = cd[i + 8];
			}
		}
	}
	if (strlen(cd) > 0) {
		pBuffer[pos++] = strToByte(cd);
	}
	fclose(in);

	return OK;
}

char strToByte(const char* pBinStr) {

	char b = 0x00;

	for (int i = 0; i < 8; i++) {
		b = b << 1;
		if (pBinStr[i] == '1') {
			b = b | 0x01;
		}
	}

	return b;
}

int writeFile(const char* pFilename, const HEAD sHead, const char* pBuffer, const int size) {

	char filename[256] = { 0 };
	strcpy(filename, pFilename);
	strcat(filename, ".huf");

	FILE* out = fopen(filename, "wb");

	fwrite(pBuffer, sizeof(HEAD), 1, out);
	fwrite(pBuffer, sizeof(char), size, out);
	fclose(out);
	out = NULL;

	return sizeof(HEAD) + strlen(pFilename) + 1 + size;

	return 0;
}
