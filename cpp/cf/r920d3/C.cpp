#include<bits/stdc++.h>
using namespace std;
#define ll long long

void solve() {
    ll a, b, n, f;
    cin >> n >> f >> a >> b;
    ll ans = 0;
    ll t1 = 0, t2 = 0;
    for (ll i = 0; i < n; i++) {
        cin >> t2;
        ans += min((t2 - t1) * a, b);
        t1 = t2;
    }
    if (ans < f) {
        cout << "YES" << endl;
    }
    else {
        cout << "NO" << endl;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    //solve();
    return 0;
}