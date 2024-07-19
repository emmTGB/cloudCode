#include<bits/stdc++.h>
using namespace std;

// 高位择优
class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        int max_int = *max_element(nums.begin(), nums.end());
        int max_len = max_int ? 31 - __builtin_clz(max_int) : -1;

        int ans = 0, msk = 0;
        unordered_set<int> s;
        for(int i = max_len; i >= 0; --i){
            s.clear();
            msk |= 1 << i;
            int new_a = ans | 1 << i;
            for(int num : nums){
                num &= msk;
                if(s.find(new_a ^ num) != s.end()){
                    ans = new_a;
                    break;
                }
                s.insert(num);
            }
        }
        return ans;
    }
};