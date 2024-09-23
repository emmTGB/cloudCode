#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int balancedStringSplit(string s) {
        int sol = 0;
        int balanced = 0;
        for(int i = 0; i < s.size(); i++){
            if(s[i] == 'L'){
                balanced++;
            }else{
                balanced--;
            }
            if(balanced == 0 && i != 0){
                sol++;
            }
        }
        return sol;
    }
};