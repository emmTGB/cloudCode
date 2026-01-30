#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string thousandSeparator(int n) {
        if (!n) return "0";
        string res = "";
        int counter = 0;
        while (n) {
            int tmp = n % 10;
            n /= 10;
            res = to_string(tmp).append(res);
            if (n && counter == 2) {
                res = string(".").append(res);
                counter = -1;
            }
            counter++;
        }
        return res;
    }
};