//
#include<iostream>
#include<string>
using namespace std;

unsigned int stack[1010];
unsigned int sTop = 0;
unsigned int maxO(char c){
    switch (c)
    {
    case 'p'/* constant-expression */:
        return (unsigned int)4294901760;
    case 'q':
        return (unsigned int)4278255360;
    case 'r':
        return (unsigned int)4042322160;
    case 's':
        return (unsigned int)3435973836;
    case 't':
        return (unsigned int)2863311530;
    default:
        return (unsigned int)0xffffffff;
    }
}

unsigned int andK(unsigned int w, unsigned int x){
    return ~(~w & ~x);
}

unsigned int orA(unsigned int w, unsigned int x){
    return w | x;
}

unsigned int noN(unsigned int x){
    return ~x;
}

unsigned int contC(unsigned int w, unsigned int x){
    return ~w | x;
}

unsigned int equalE(unsigned int w, unsigned int x){
    return w == x ? (unsigned int)~0 : 0;
}

int main(){
    string s;
    while(cin>>s){
        if(!s.compare("0")){
            break;
        }
        sTop = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            switch ((int)s[i])
            {
            case 75:
                sTop --;
                stack[sTop - 1] = andK(stack[sTop], stack[sTop - 1]);
                break;
            case 65:
                sTop --;
                stack[sTop - 1] = orA(stack[sTop], stack[sTop - 1]);
                break;
            case 78:
                stack[sTop - 1] = noN(stack[sTop - 1]);
                break;
            case 67:
                sTop --;
                stack[sTop - 1] = contC(stack[sTop], stack[sTop - 1]);
                break;
            case 69:
                sTop --;
                stack[sTop - 1] = equalE(stack[sTop], stack[sTop - 1]);
                break;
            default:
                stack[sTop] = maxO(s[i]);
                sTop++;
                break;
            }
        }
        if(stack[--sTop] == 4294967295){
            cout<<"tautology"<<endl;
        }else{
            cout<<"not"<<endl;
        }
    }
}
