#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        partial_sum(nums.begin(), nums.end(), nums.begin());
        unordered_set<int> st;
        st.insert(0);
        int res = 0;
        for (int n : nums) {
            if (st.find(n - target) != st.end()) {
                ++res;
                st.clear();
            }
            st.insert(n);
        }
        return res;
    }
};