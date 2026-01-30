#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool uniqueOccurrences(vector<int>& a) {
        unordered_map<int, int> m;
        for (int x : a) {
            m[x]++;
        }
        unordered_set<int> s;
        for (const auto& it : m) {
            s.insert(it.second);
        }
        return m.size() == s.size();
    }
};