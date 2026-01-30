#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        int max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        int sum = max;
        for (int i = k; i < nums.size(); i++) {
            sum += nums[i] - nums[i - k];
            max = max > sum ? max : sum;
        }
        return (double)max / k;
    }
};