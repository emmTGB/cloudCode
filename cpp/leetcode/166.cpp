#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    string fractionToDecimal(int n, int d) {
        long a = n, b = d;
        if (a % b == 0) return to_string(a / b);
        string res = "";
        if (a * b < 0) res.append("-");
        a = abs(a), b = abs(b);
        res.append(to_string(a / b) + ".");
        a %= b;
        unordered_map<long, int> mp;
        while (a != 0) {
            mp[a] = res.length();
            a *= 10;
            res.append(to_string(a / b));
            a %= b;
            if (mp[a]) {
                int u = mp[a];
                return res.substr(0, u) + "(" + res.substr(u) + ")";
            }
        }
        return res;
    }
};