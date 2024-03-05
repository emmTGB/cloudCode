#pragma once

#ifndef COMPRESS_H
#define COMPRESS_H

struct HEAD
{
	char type[4];
	int length;
	int weight[256];
};

int compress(const char* pFilename);

int initHead(const char* pFilename, HEAD& sHead);

int encode(const char* pFilename, char** pHC, char* pBuffer, const int nSize);

char strToByte(const char* pBinStr);

int writeFile(const char* pFilename, const HEAD sHead, const char* pBuffer, const int size);

#endif // !COMPRESS_H
