//
#include<iostream>
using namespace std;

char stack[101];
int sTop = 0;

int main(){
    string s;
    while(cin>>s){
        if(!s.compare("0")){
            break;
        }
        for(int i = s.length() - 1; i >= 0; i++){
            switch (s[i])
            {
            case 'K':
                break;
            case 'A':
                break;
            case 'N':
                break;
            case 'C';
                break;
            case 'E';
                break;
            default:
                stack[sTop] = s[i];
                sTop++;
                break;
            }
        }
    }
}