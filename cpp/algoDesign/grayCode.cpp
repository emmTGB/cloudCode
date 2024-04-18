#include<iostream>
using namespace std;

int** a;

void grayCode(int n, int num) {
    if (n == 1) {
        a[0][0] = 0;
        a[1][0] = 1;
    }
    else {
        num /= 2;
        grayCode(n - 1, num);
        for (int i = 0; i < num; i++) {
            a[i][n - 1] = 0;
        }
        for (int i = num; i < 2 * num; i++) {
            a[i][n - 1] = 1;
            for (int j = 0; j < n - 1; j++) {
                a[i][j] = a[2 * num - i - 1][j];
            }
        }
    }
}

int main() {
    int n;
    cin >> n;
    int num = 1 << n;
    a = new int* [num];
    for (int i = 0; i < num; i++) {
        a[i] = new int[n];
    }
    grayCode(n, num);
    for (int i = 0; i < num; i++) {
        for (int j = n - 1; j >= 0; j--) {
            cout << a[i][j];
        }
        cout << endl;
    }
}