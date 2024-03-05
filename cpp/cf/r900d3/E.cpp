#include<bits/stdc++.h>
using namespace std;
#define pii pair<int, int>
#define mii map<int, int>
void solve() {
    int n;
    cin >> n;
    vector<int> num(n);
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        num[i] = tmp;
    }
    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        int k, l, r = -1;
        cin >> l >> k;
        l--;
        if (l >= n || num[l] < k) {
            cout << r << ' ';
            continue;
        }
        else if (k == 0) {
            cout << n << ' ';
            continue;
        }
        int tmp = num[l];
        for (int j = l; j < n; j++) {
            tmp &= num[j];
            if (tmp >= k) {
                r = j;
            }
            else {
                break;
            }
        }
        cout << r + 1 << ' ';
    }
    cout << endl;
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}