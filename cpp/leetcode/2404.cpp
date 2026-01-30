#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int mostFrequentEven(vector<int>& nums) {
        int res = -1;
        unordered_map<int, int> c;
        for (int x : nums) {
            if (x % 2 == 0) {
                int cnt = ++c[x];
                if(cnt > c[res] || cnt == c[res] && x < res) res = x;
            }
        }
        return res;
    }
};