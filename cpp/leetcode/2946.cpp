#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool areSimilar(vector<vector<int>>& mat, int k) {
        if (k == 0)
            return true;

        int n = mat[0].size();
        k %= n;
        for (auto& v : mat) {
            for (int i = 0; i < n; i++) {
                if(v[i] != v[(i + k) % n])
                    return false;
            }
        }
        return true;
    }
};