#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int deleteAndEarn(vector<int>& nums) {
        int max_val = 0;
        for(auto n : nums){
            max_val =max(n,max_val);
        }
        vector<int> sum(max_val+1,0);
        for(auto n: nums){
            sum[n]+=n;
        }
        int first=sum[0], second=max(sum[0],sum[1]);
        for(int i = 2 ; i <= max_val;++i){
            int tmp = second;
            second = max(sum[i]+first,second);
            first = tmp;
        }
        return second;
    }
};