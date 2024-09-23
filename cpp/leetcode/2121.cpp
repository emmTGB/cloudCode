#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<long long> getDistances(vector<int>& arr) {
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < arr.size(); i++) {
            mp[arr[i]].push_back(i);
        }
        vector<long long> ans(arr.size());
        for (auto& [key, val] : mp) {
            long long sum = 0LL;
            for(int i : val) sum += i - val[0];
            ans[val[0]] = sum;
            for(int i = 1; i < val.size(); i++){
                sum += (2 * i - val.size()) * (val[i] - val[i - 1]);
                ans[val[i]] = sum;
            }
        }
        return ans;
    }
};