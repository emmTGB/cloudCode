#include<bits/stdc++.h>
using namespace std;

class Solution {
    const int vowel_msk = 0b100000100000100010001;

    int q_sqrt(int x){
        int res = 1;
        for(int i = 2; i * i <= x; i++){
            int i2 = i * i;
            while(x % i2 == 0){
                res *= i;
                x /= i2;
            }
            if(x % i == 0){
                res *= i;
                x /= i;
            }
        }
        if(x > 1){
            res *= x;
        }
        return res;
    }

public:
    int beautifulSubstrings(string s, int k) {
        k = q_sqrt(4 * k);
        unordered_map<int, int> cnt;
        int n = s.length();
        int sum = n;
        cnt[(k - 1) <<16 | sum] ++;  // 标记 -1 mod k和 s[-1]
        int ans = 0;
        for(int i = 0; i < n; ++i){
            int bit = (vowel_msk>>(s[i] - 'a'))&1;
            sum += bit * 2 - 1;
            ans += cnt[(i % k) <<16 | sum] ++;  // 标记 i mod k和 前缀和s[i]出现一次
            // n个同组节点可以得到 n*(n-1)/2个子串
        }
        return ans;
    }
};