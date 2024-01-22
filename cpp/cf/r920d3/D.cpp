#include<bits/stdc++.h>
using namespace std;
#define ll long long

void solve() {
    ll n, k; cin >> n >> k;
    vector<ll> a(n), b(k);
    for (auto& i : a) cin >> i;
    for (auto& i : b) cin >> i;
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    ll mina = 0, minb = 0, maxb = k - 1;
    ll ans = 0;
    while (mina < n) {
        if (abs(a[mina] - b[minb]) > abs(a[mina] - b[maxb])) {
            ans += abs(a[mina] - b[minb]);
            ++minb; ++mina;
        }
        else {
            ans += abs(a[mina] - b[maxb]);
            --maxb; ++mina;
        }    }
    cout << ans << endl;
}

int main() {
    solve();
}