//
#include<bits/stdc++.h>
using namespace std;
#define ll long long
#define pii pair<int, int>
#define mii map<int, int>

void solve(){
    int c, t;
    cin>>c;
    for(int i = 0; i < c; i++){
        int sum = 0;
        mii fir, end, cnt;
        vector<int> a;
        cin >> t;
        for(int j = 0; j < t; j++){
            cin>>a[j];
            if(cnt[a[j]] == 0){
                cnt[a[j]] = 1;
                fir[a[j]] = j;
                end[a[j]] = j;
            }else{
                end[a[j]] = j;
            }
        }
        for(int j = 0; j < t; j++){
            if(fir[a[j]] == j) ++sum;
            if(end[a[j]] == j) res += sum;
        }
    }
}

int main(){
    solve();
}