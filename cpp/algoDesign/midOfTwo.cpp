#include<iostream>
using namespace std;

double mid(int a[], int b[], int n) {
    int j = n - 1, k = 0;
    if (a[j] <= b[k]) {
        return (a[n - 1] + b[0]) / 2.0;
    }
    while (a[j] > b[k]) {

    }
    return 0;
}


int main() {
    int n;
    cin >> n;
    int* a = new int[n];
    int* b = new int[n];
    for (int i = 0;i < n;i++) {
        cin >> a[i];
    }
    for (int i = 0;i < n;i++) {
        cin >> b[i];
    }
    if (a[n - 1] < b[n - 1]) {
        swap(a, b);
    }
}