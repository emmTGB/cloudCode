#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        for (int i = 1; i < nums.size(); ++i) {
            nums[i] += nums[i - 1];
        }
        for (int& x : queries) {
            x = upper_bound(nums.begin(), nums.end(), x) - nums.begin();
        }
        return queries;
    }
};