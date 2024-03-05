//
#include<bits/stdc++.h>
using namespace std;
#define pii pair<int, int>

void solve() {
    multiset<pii> x, y;
    int n, p, q;
    char ch;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> ch >> p >> q;
        if (ch == '+') {
            x.insert({ p, q });
            y.insert({ q, p });
        }
        else {
            x.erase(x.find({ p, q }));
            y.erase(y.find({ q, p }));
        }

        if (x.size() && x.rbegin()->first > y.begin()->first) {
            cout << "YES";
        }
        else {
            cout << "NO";
        }
        cout << endl;
    }
}

int main() {
    solve();
}