#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& digits) {
        vector<int> fq(10);
        for (int x : digits) {
            ++fq[x];
        }
        vector<int> ans;
        for (int i = 100; i < 1000; i += 2) {
            vector<int> f(10);
            int tmp = i;
            while (tmp) {
                ++f[tmp % 10];
                tmp /= 10;
            }
            bool b = true;
            for (int j = 0; j < 10; ++j) {
                if (f[j] > fq[j]) {
                    b = false;
                    break;
                }
            }
            if (b) ans.push_back(i);
        }
        return ans;
    }
};