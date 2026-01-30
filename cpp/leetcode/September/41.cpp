#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int firstMissingPositive(vector<int>& n) {
        int l = n.size();
        int i = 0;
        for(i = 0; i < l; ++i){
            while (!(n[i] <= 0 || n[i] > l || n[i] == n[n[i] - 1])) {  // 排除数组角标范围外以及对应角标值正确的数
                swap(n[n[i] - 1], n[i]);
            }
        }
        for (i = 0; i < l; ++i) {
            if (i + 1 != n[i]) {
                return i + 1;
            }
        }
        return 1 + i;
    }
};