//
#include<bits/stdc++.h>
using namespace std;

int solve(){
    int n;
    cin>>n;
    if(!n) return 0;
    vector<int> a(n);
    for(int i = 0; i < n; i++){
        cin>>a[i];
    }
    if(a[0] == n - 1) return 0;
    if(a[0] == n - 2) return 1;
    vector<int> res(n);
    for(int i = 0; i < n; i++){
        if(i + a[i] < n){
            res[i + a[i]] = min(res[i - 1], res[i + a[i]]);
        }
        res[i] = min(!i ? 1 : (res[i - 1] + 1), res[i]);
    }
    return res[n];
}

int main(){
    int t;
    cin>>t;
    for(int i = 0; i < t; i++){
        cout<<solve()<<'\n';
    }
}