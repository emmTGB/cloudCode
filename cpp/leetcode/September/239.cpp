#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> res(n - k + 1);
        deque<int> q;
        for (int i = 0; i < n;++i) {
            while (!q.empty() && nums[q.back()] <= nums[i]) {
                q.pop_back();
            }  // 维护一个单调递减队列，队首为目前最大
            q.push_back(i);

            int l = i - k + 1;  // 此值一步一动，故而出队和结果都只会执行一次
            if (q.front() < l) {
                q.pop_front();
            }

            if (l >= 0) {
                res[l] = nums[q.front()];
            }
        }
        return res;
    }
};