#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string removeStars(string s) {
        vector<char> v;
        for(char c: s){
            if(c =='*'){
                v.pop_back();
            }else{
                v.push_back(c);
            }
        }
        return string(v.begin(), v.end());
    }
};