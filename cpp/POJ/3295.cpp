//
#include<iostream>
using namespace std;

char stack[101];
int sTop = 0;
int min(char c){
    switch (c)
    {
    case 'p'/* constant-expression */:
        return 0b1111111100000000;
    case 'q':
        return 0b1111000011110000;
    case 'r':
        return 0b1111000000001111;
    case 't':
        return 0b0000111111110000;
    default:
        return 0xffffff;
    }
}

int andK(int w, int x){
    return ~(~w & ~x);
}

int orA(int w, int x){
    return w | x;
}

int noN(int x){
    return ~x;
}

int contC(int w, int x){
    return (~w) | x;
}

int equalE(int w, int x){
    return w == x ? 0b1111111111111111 : 0b0000000000000000;
}

int main(){
    string s;
    while(cin>>s){
        if(!s.compare("0")){
            break;
        }
        for(int i = s.length() - 2; i >= 0; i--){
            switch (s[i])
            {
            case 'K':
                sTop --;
                stack[sTop - 1] = andK(stack[sTop], stack[sTop - 1]);
                break;
            case 'A':
                sTop --;
                stack[sTop - 1] = orA(stack[sTop], stack[sTop - 1]);
                break;
            case 'N':
                sTop--;
                stack[sTop - 1] = noN(stack[sTop - 1]);
                break;
            case 'C':
                sTop --;
                stack[sTop - 1] = contC(stack[sTop], stack[sTop - 1]);
                break;
            case 'E':
                sTop --;
                stack[sTop - 1] = equalE(stack[sTop], stack[sTop - 1]);
                break;
            default:
                stack[sTop] = min(s[i]);
                sTop++;
                break;
            }
        }
        int ans = stack[--sTop];
        if(stack[sTop] == 0b1111111111111111){
            cout<<"ok"<<endl;
        }else{
            cout<<"not"<<endl;
        }
    }
}
