#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> mp;
        int m = INT32_MAX;
        for (int i = 0; i < cards.size(); ++i) {
            if (mp.find(cards[i]) != mp.end()) {
                m = min(m, 1 + i - mp[cards[i]]);
            }
            mp[cards[i]] = i;
        }
        if (m == INT32_MAX) m = -1;
        return m;
    }
};