#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int longestConsecutive(vector<int>& n) {
        unordered_set<int> s;
        for (int x : n) {
            s.insert(x);
        }
        int ans = 0;
        for (int x : s) {
            if (s.find(x - 1) == s.end()) {
                int cur = x;
                int len = 1;
                while (s.find(cur + 1) != s.end()) {
                    cur += 1;
                    len++;
                }
                ans = max(len, ans);
            }
        }
        return ans;
    }
};

class BetterSolution {
public:
    int longestConsecutive(vector<int>& n) {
        if (n.size() == 0) return 0;

        int ans = 0;
        int tmp = 1;
        sort(n.begin(), n.end());
        for (int i = 1; i < n.size(); ++i) {
            if (n[i] == n[i - 1]) {
                continue;
            }
            if (n[i] == n[i - 1] + 1) {
                tmp++;
            }
            else {
                ans = max(ans, tmp);
                tmp = 1;
            }
        }
        return max(ans, tmp);
    }
};