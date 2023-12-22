#include<bits/stdc++.h>
using namespace std;

double a = 1e-6;

double f(double x) {
    return x * x * x + 2 * x * x + 10 * x - 20;
}

double df(double x) {
    return 3 * x * x + 4 * x + 10;
}

bool acc(double x1, double x2) {
    return fabs(x1 - x2) < a;
}

void newton() {
    double x0 = 2;
    double x1;
    while (df(x0)) {
        x1 = x0 - f(x0) / df(x0);
        cout << x1 << endl;
        if (acc(x0, x1))
            return;
        x0 = x1;
    }
}

int main() {
    newton();
}