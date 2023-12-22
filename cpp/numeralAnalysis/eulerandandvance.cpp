#include<bits/stdc++.h>
using namespace std;

double step = 0.1;
int N = 10;

double func(double x, double y) {
    return y - (2 * x) / y;
}

void euler() {
    double x = 0, y = 1;

    for (int i = 0; i < N;i++) {
        y = y + step * func(x, y);
        x += step;
        cout << y << endl;
    }
}

void adv_euler() {
    double x = 0, y = 1;

    for (int i = 0;i < N;i++) {
        double yp = y + step * func(x, y);
        x += step;
        double yc = y + step * func(x, yp);
        y = (yp + yc) / 2;
        cout << x << " " << y << endl;
    }
}

int main() {
    euler();
    adv_euler();
}