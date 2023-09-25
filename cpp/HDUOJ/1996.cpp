#include<iomanip>
#include<iostream>
#include<math.h>
using namespace std;
typedef long long ll;

int main(){
    int n;
    cin>>n;
    for(int i = 0; i < n; i++){
        ll n;
        cin>>n;
        cout<<fixed<<setprecision(0)<<(ll)pow(3, n)<<endl;
    }
}