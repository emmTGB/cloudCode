//
#include<iostream>
#include<cmath>
#include<string>
#include<vector>
#include<set>
#include<map>
using namespace std;

void solve();

int main(){
    int _case, n;
    long long a;

    cin>>_case;
    for(int i = 0; i < _case; i++)
        solve();
}

void solve(){
    int n;
    int a;
    map<int, int> m;
    cin>>n;

    auto decompose = [&] (int x) -> void{
        for(int i = 2; i * i <= x; i++){
            while(x % i == 0){
                m[i]++, x /= i;
            }
        }
        if(x > 1) m[x]++;
    };

    for(int i = 0; i < n; i++){
        cin>>a;
        decompose(a);
    }
    bool ok = 1;
    for(auto [x, y] : m){
        if(y % n != 0){
            ok = 0;
            break;
        }
    }
    if(ok) cout<<"YES\n";
    else cout<<"NO\n";
}