#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    void moveZeroes(vector<int>& n) {
        if (n.size() <= 1) return;
        int cur = 0;
        for (int i = 0; i < n.size(); ++i) {
            if (n[i]) {
                n[cur++] = n[i];
            }
        }
        for (; cur < n.size(); ++cur) {
            n[cur] = 0;
        }
        return;
    }
};