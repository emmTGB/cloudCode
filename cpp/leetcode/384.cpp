#include<bits/stdc++.h>
using namespace std;


class Solution {
private:
    vector<int>* nums, * shuffled;
public:
    Solution(vector<int>& nums) {
        this->nums = &nums;
        shuffled = new vector<int>(nums);
    }

    vector<int> reset() {
        return *nums;
    }

    vector<int> shuffle() {
        random_shuffle(shuffled->begin(), shuffled->end());
        return *shuffled;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * vector<int> param_1 = obj->reset();
 * vector<int> param_2 = obj->shuffle();
 */