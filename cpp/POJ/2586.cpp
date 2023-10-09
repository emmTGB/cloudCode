// AC
#include<iostream>
using namespace std;

int main(){
    long long s, d;
    long long ans;
    while(cin>>s>>d){
        if(4 * s < d){
            ans = 10 * s - 2 * d;
        }else if(3 * s < 2 * d){
            ans = 8 * s - 4 * d;
        }else if(2 * s < 3 * d){
            ans = 6 * s - 6 * d;
        }else if(s < 4 * d){
            ans = 3 * s - 9 * d;
        }else{
            ans = -1;
        }
        if(ans > 0){
            cout<<ans;
        }else{
            cout<<"Deficit";
        }
        cout<<endl;
    }
}