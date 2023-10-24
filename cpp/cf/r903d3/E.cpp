//
#include<bits/stdc++.h>
#define inf 0xfffff
using namespace std;

int solve() {
    int n;
    cin >> n;
    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    vector<int> res(n + 1, inf);
    res[0] = 0;
    for (int i = 1; i <= n; i++) {  // dp
        if (i + a[i] <= n) {
            res[i + a[i]] = min(res[i - 1], res[i + a[i]]);
        }
        res[i] = min(res[i - 1] + 1, res[i]);
    }
    return res[n];
}

int main() {
    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        cout << solve() << '\n';
    }
}