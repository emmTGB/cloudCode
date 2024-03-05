#include<bits/stdc++.h>
using namespace std;
#define ll long long

// 相同不动，看0变1还是1变0，然后min + max - min即为答案

void solve() {
    ll n; cin >> n;
    string s, f;
    cin >> s >> f;
    int a = 0, b = 0;
    for (int i = 0; i < n;i++) {
        if (s[i] == f[i]) continue;
        if (s[i] == '0') ++a;
        else ++b;
    }
    cout << max(a, b) << endl;
}

int main() {
    int t = 1; cin >> t;
    while (t--) solve();
    return 0;
}