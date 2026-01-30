#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<int>> findDifference(vector<int>& n1, vector<int>& n2) {
        vector<vector<int>> ans(2);
        unordered_set<int> s1, s2;
        for (int x : n1) {
            s1.insert(x);
        }
        for (int x : n2) {
            s2.insert(x);
        }
        for (int x : s1) {
            if (!s2.count(x)) {
                ans[0].push_back(x);
            }
        }
        for (int x : s2) {
            if (!s1.count(x)) {
                ans[1].push_back(x);
            }
        }
        return ans;
    }
};