#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string decodeString(string s) {
        int m = 0;
        vector<int> ms;
        string n = "";
        vector<string> ss = {""};
        for (char c : s) {
            if (isdigit(c)) {
                m = m * 10 + (c - '0');
            }
            else if (c == '[') {
                ss.push_back("");
                ms.push_back(m);
                m = 0;
            }
            else if (isalpha(c)) {
                ss.back() += c;
            }
            else if (c == ']') {
                string tmp = ss.back();
                int cnt = ms.back();
                ms.pop_back(), ss.pop_back();
                while (cnt--) ss.back() += tmp;
            }
        }
        return ss.back();
    }
};