#include<bits/stdc++.h>
using namespace std;
#define pii pair<int, int>
#define mii map<int, int>
typedef long long ll;

inline string read()
{
    char ch = getchar();
    string st1 = "";
    while (!((ch >= 'a') && (ch <= 'z')))
        ch = getchar();
    while ((ch >= 'a') && (ch <= 'z'))
        st1 += ch, ch = getchar();
    return st1;
}
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
    set<ll> x;
    string s;
    ll n, k, q, tmp;
    cin >> n >> k;
    s = read();
    vector<ll> l(k+5), r(k+5);
    for (int i = 0; i < k; i++) {
        scanf("%lld", &tmp);
        l.push_back(tmp - 1);
    }
    for (int i = 0; i < k; i++) {
        scanf("%lld", &tmp);
        r.push_back(tmp - 1);
    }
    cin >> q;
    for (ll i = 0; i < q; i++) {
        scanf("%lld", &tmp);
        if (x.count(tmp - 1)) {
            x.erase(tmp - 1);
        }
        else {
            x.insert(tmp - 1);
        }
    }
    vector<int> flag(n + 5);
    for (auto it = x.begin(); it != x.end(); it++) {
        tmp = *it;
        ll j = k / 2;
        cout << "**" << j;
        while (j > -1 && j < k) {
            if (l[j] > tmp) {
                --j;
            }
            else if (r[j] < tmp) {
                ++j;
            }
            else {
                break;
            }
        }cout << "@@" << tmp << ' ' << j;
        ll a = min(tmp, l[j] + r[j] - tmp);
        ll b = max(tmp, l[j] + r[j] - tmp);
        ++flag[a], --flag[b];
        cout << "##" << a << ' ' << b;
    }
    vector<int> sol(n + 5);
    int now = 0;
    for (int i = 0; i < n; i++) {
        now += flag[i];
        sol[i] = now % 2;
    }
    for (int i = 0; i < n; i++)cout << flag[i];
    cout << endl;
    for (int i = 0; i < n; i++)cout << sol[i];
    for (int i = 0; i < k; i++) {
        for (int j = l[i]; 2 * j <= r[i] + l[i]; j++) {
            if (sol[i] != 0)cout << "wok";
            // swap(s[j], s[l[i] + r[i] - j]);

        }
    }
    cout << s << endl;
}

int main() {
    ll t;
    cin >> t;
    while (t--) {
        solve();
    }
}