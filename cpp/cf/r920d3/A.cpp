#include<bits/stdc++.h>
using namespace std;
#define ll long long

void solve() {
    ll x, y;
    ll xmax = -1e9, xmin = 1e9, ymax = -1e9, ymin = 1e9;

    for (int i = 0; i < 4; i++) {
        cin >> x >> y;
        xmax = max(xmax, x);
        xmin = min(xmin, x);
        ymax = max(ymax, y);
        ymin = min(ymin, y);
    }
    cout << (xmax - xmin) * (ymax - ymin) << endl;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    ll tc = 1;
    cin >> tc;
    for (ll t = 1; t <= tc; t++) {
        solve();
    }
    return 0;
}