#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxOperations(vector<int>& n, int k) {
        sort(n.begin(), n.end());
        int j = n.size() - 1;
        int i = 0;
        int ans = 0;
        while (i < j) {
            if (n[i] + n[j] == k) {
                ++i;
                --j;
                ++ans;
            }
            else if (n[i] + n[j] < k) {
                ++i;
            }
            else {
                --j;
            }
        }
        return ans;
    }
};


class HashSolution {
public:
    int maxOperations(vector<int>& n, int k) {
        unordered_map<int, int> m;
        int ans = 0;
        for (int x : n) {
            auto it = m.find(k - x);
            if (it != m.end() && it->second) {
                it->second--;
                ans++;
            }
            else {
                m[x]++;
            }
        }
        return ans;
    }
};