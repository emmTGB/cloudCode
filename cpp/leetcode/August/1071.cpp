#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool check(string str, string x) {
        int l = str.length() / x.length();
        string ans = "";
        for (int i = 1; i <= l; ++i) {
            ans = ans + x;
        }
        return ans == str;
    }

    string gcdOfStrings(string str1, string str2) {
        int l1 = str1.length(), l2 = str2.length();

        for (int l = min(l1, l2); l > 0; --l) {
            if (l1 % l == 0 && l2 % l == 0) {
                string x = str1.substr(0, l);
                if (check(str1, x) && check(str2, x)) return x;
            }
        }
        return  "";
    }
};