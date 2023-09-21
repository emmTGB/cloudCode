#include<iomanip>
#include<iostream>
using namespace std;
typedef long long ll;

int main(){
    ll n, m;
    do{
        cin>>m>>n;
        ll p = 1;
        for(int i = 1; p <= n, i <= m; i++, p *= 2){
            if(n % (p * 2) == p){
                cout<<i<<endl;
                break;
            }
        }
    }while(!(m == 0 && n == 0));
    return 0;
}