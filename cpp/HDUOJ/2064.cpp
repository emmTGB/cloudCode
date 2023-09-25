#include<iomanip>
#include<iostream>
using namespace std;
typedef long long ll;

ll qpow(ll a, ll n){
    ll ans = 1;
    while(n){
        if(n&1)        //如果n的当前末位为1
            ans *= a;  //ans乘上当前的a
        a *= a;        //a自乘
        n >>= 1;       //n往右移一位
    }
    return ans;
}

int main(){
    ll n;
    while(cin>>n){
        printf("%lld\n", qpow(3, n) - 1);
    }
    return 0;
}