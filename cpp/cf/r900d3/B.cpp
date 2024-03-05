#include<bits/stdc++.h>
using namespace std;
#define pii pair<int, int>
#define mii map<int, int>
typedef long long ll;

void read(ll& x) {
    int s = 0, w = 1;
    char ch = getchar();
    while (ch < '0' || ch > '9') {
        if (ch == '-') {
            w = -1;
            ch = getchar();
        }
    }
    while (ch >= '0' && ch <= '9') {
        s = s * 10 + ch - '0';
        ch = getchar();
    }
    x = s * w;
}

ll read() {
    int s = 0, w = 1;
    char ch = getchar();
    while (ch < '0' || ch > '9') {
        if (ch == '-') {
            w = -1;
            ch = getchar();
        }
    }
    while (ch >= '0' && ch <= '9') {
        s = s * 10 + ch - '0';
        ch = getchar();
    }
    return 1ll * s * w;
}

void _print(ll x) {
    if (x > 9)_print(x / 10);
    putchar(x % 10 + '0');
}

inline void print(ll x) {
    if (x < 0) {
        x = -x;
        putchar('-');
    }
    _print(x);
}

ll qmm(ll a, ll b, ll mod)
{
    ll ans = 0;
    while (b)
    {
        if (b & 1)
            ans = (ans + a) % mod;
        a <<= 1;
        b >>= 1;
    }
    return ans;
}

ll qp(ll x, ll p)
{
    ll ans = 1;
    while (p)
    {
        if (p & 1)
            ans *= x;
        p >>= 1;
        x *= x;
    }
    return ans;
}

void solve() {
    ll t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        cout << i + 7;
        if (i < t - 1) cout << ' ';
    }
    cout << endl;
}

int main() {
    ll t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}