#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minOperations(string s1, string s2, int x) {
        if(s1 == s2) return 0;
        vector<int> v;
        for(int i=0;i<s1.length();i++){
            if(s1[i] != s2[i])
                v.push_back(i);
        }
        if(v.size() % 2 != 0) return -1;
        int f0 = 0, f1 = x;
        for(int i = 1; i < v.size(); i ++){
            int new_f = min(f1 + x, f0 + (v[i] - v[i - 1]) * 2);
            f0 = f1;
            f1 = new_f;
        }
        return f1 / 2;
    }
};