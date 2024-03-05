#include"huff.h"
#include"compress.h"

#include<iostream>

int main() {
	std::cout << "____bitmap compression____" << std::endl
		<< "input your file path:";
	char filepath[256];
	std::cin >> filepath;
	if (compress(filepath) == OK) {
		std::cout << "OK" << std::endl;
	}
	else {
		std::cout << "ERR" << std::endl;
	}

	return 0;
}