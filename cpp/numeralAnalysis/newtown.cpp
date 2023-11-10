#include<iostream>
using namespace std;

double diffTable[100][100];

double newtown(const double* x, const double* y, double a, double n) {
    for (int j = 1; j < n; j++) {
        diffTable[0][j] = (y[j] - y[j - 1]) / (x[j] - x[j - 1]);
    }
    for (int i = 1; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            diffTable[i][j] = (diffTable[i - 1][j] - diffTable[i - 1][j - 1]) / (x[j] - x[j - i - 1]);
        }
    }

    double res = y[0];
    double pow = 1;
    for (int i = 0; i < n; i++) {
        pow *= a - x[i];
        res += pow * diffTable[i][i + 1];
    }

    return res;
}