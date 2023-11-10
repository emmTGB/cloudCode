#include<iostream>
using namespace std;

double lagrange(const double* x, const double* y, double a, int n) {
    double res;
    for (int i = 0; i < n; i++) {
        double pu = y[i], pd = 1;
        for (int j = 0; j < n; j++) {
            if (j == i) continue;
            pu *= a - x[j];
            pd *= x[i] - x[j];
        }
        res += pu / pd;
    }
    return res;
}