#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int>frequencySort(vector<int>& nums) {
        map<int, int> mp;

        for (int i : nums) {
            mp[i]++;
        }
        vector<pair<int, int>> cnt;
        for (auto& x : mp) {
            cnt.push_back(make_pair(
                x.first, x.second
            ));
        }
        sort(cnt.begin(), cnt.end(), [](pair<int, int>& a, pair<int, int>& b) {
            if (a.second == b.second) {
                return a.first > b.first;
            }
            return a.second < b.second;
            });

        vector<int> ans;
        for (auto& p : cnt) {
            int num = p.first;
            int fre = p.second;
            for (int i = 0; i < fre; ++i) {
                ans.push_back(num);
            }
        }
        return ans;
    }
};