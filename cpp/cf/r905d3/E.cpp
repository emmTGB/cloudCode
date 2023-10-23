//
#include <bits/stdc++.h>
using namespace std;
#define ll long long


void solve(){
    ll res, m, n, p, q;
    cin>>m;
    for(ll i = 0; i < m; i++){
        cin>>n;
        res = 0;
        p = 0;
        for(ll j = 0; j < n; j++){
            cin>>q;
            while(q < p){
                q *= 2;
                ++res;
            }
            g += t;
            g = max(0ll, g);
            res += g;
            p = q;
        }
        cout<<res;
    }
}

int main()
{
    solve();
}