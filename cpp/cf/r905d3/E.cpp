//
#include <bits/stdc++.h>
using namespace std;
#define ll long long


void solve(){
    ll res, m, n, p, q, g;
    scanf("%lld", &m);
    for(ll i = 0; i < m; i++){
        scanf("%lld", &n);
        p = 1, res = 0, g = 0;

        for(ll j = 0; j < n; j++){
            scanf("%lld", &q);
            ll tmp = q, t = 0;
            while(tmp < p){
                tmp <<= 1;
                ++t;
            }
            tmp = p << 1;
            while(tmp <= q){
                tmp <<= 1;
                --t;
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