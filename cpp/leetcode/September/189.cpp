#include<bits/stdc++.h>
using namespace std;
// 原地做法，基础题别忘了
class Solution {
public:
    void rotate(vector<int>& n, int k) {
        k %= n.size();
        reverse(n.begin(), n.end());
        reverse(n.begin(), n.begin() + k);
        reverse(n.begin() + k, n.end());
    }
};