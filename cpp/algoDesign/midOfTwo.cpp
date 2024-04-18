#include<iostream>
using namespace std;

int* a, * b;

double find_k(int l1, int r1, int l2, int r2, int k) {
    int cur_1 = r1 - l1 + 1;
    int cur_2 = r2 - l2 + 1;
    if (k == 1) {
        return min(a[l1], b[l2]);
    }
    if (cur_1 == 0) {
        return b[l2 + k - 1];
    }
    if (cur_2 == 0) {
        return a[l1 + k - 1];
    }
    int m1 = l1 + min(cur_1, k / 2) - 1;
    int m2 = l2 + min(cur_2, k / 2) - 1;
    if (a[m1] <= b[m2]) {
        return find_k(m1 + 1, r1, l2, m2, k - (m1 - l1 + 1));
    }
    else {
        return find_k(l1, r1, m2 + 1, r2, k - (m2 - l2 + 1));
    }
}

double mid(int n) {
    int n1 = find_k(0, n - 1, 0, n - 1, n);
    int n2 = find_k(0, n - 1, 0, n - 1, n + 1);
    return (n1 + n2) / 2.0;
}


int main() {
    int n;
    cin >> n;
    a = new int[n];
    b = new int[n];
    for (int i = 0;i < n;i++) {
        cin >> a[i];
    }
    for (int i = 0;i < n;i++) {
        cin >> b[i];
    }
    cout << mid(n) << endl;
}