// 双指针前后缀


#include<bits/stdc++.h>
using namespace std;

class Solution {
private:
    vector<string> split(string s) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, ' ')) {
            res.emplace_back(item);
        }
        return res;
    }
public:
    bool areSentencesSimilar(string st1, string st2) {
        auto w1 = split(st1);
        auto w2 = split(st2);
        if (w1.size() < w2.size()) {
            swap(w1, w2);
        }
        int m = w1.size(), n = w2.size();
        int i = 0, j = 0;
        while (i < n && w1[i] == w2[i]) {
            ++i;
        }
        while (j < n && w1[m - 1 - j] == w2[n - 1 - j]) {
            ++j;
        }
        return i + j >= n;
    }
};