#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int pivotIndex(vector<int>& n) {
        int s = reduce(n.begin(), n.end());
        int l_s = 0;
        for (int i = 0; i < n.size(); ++i) {
            if (l_s * 2 == s - n[i]) {
                return i;
            }
            l_s += n[i];
        }
        return -1;
    }
};