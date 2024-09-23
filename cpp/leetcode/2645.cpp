#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int addMinimum(string s) {

        int ans = s[0] + 2 - s.back();
        for (int i = 1; i < s.length(); i++) {
            ans += (s[i] + 2 - s[i - 1]) % 3;
        }
        return ans;

    }
};