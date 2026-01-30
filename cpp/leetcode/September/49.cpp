#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& s) {
        unordered_map<string, vector<string>> m;
        for (string& l : s) {
            string t = l;
            ranges::sort(t);
            m[t].push_back(l);
        }
        vector<vector<string>> ans;
        ans.reserve(m.size());
        for (auto& it : m) {
            ans.push_back(it.second);
        }
        return ans;
    }
};