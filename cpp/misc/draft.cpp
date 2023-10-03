#include<iostream>
#include<hash_fun.h>
#include<string>
using namespace std;

int main(){
    double dec = 3.14159265;
    size_t hashStr = hash<double>()(dec);
    cout<<hashStr;
    cout<<"看得到吗"<<endl;
}