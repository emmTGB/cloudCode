#include<bits/stdc++.h>
using namespace std;

// class Solution {
// public:
//     int sumSubseqWidths(vector<int>& nums) {
//         sort(nums.begin(), nums.end());
//         int n = nums.size();
//         if (n == 1) {
//             return 0;
//         }
//         long long ans = 0;
//         for (int i = 0; i < n - 1; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 int sel = n - j + i - 1;
//                 long long times = 1;
//                 while (sel > 50) {
//                     times <<= 50;
//                     sel -= 50;
//                     times %= 1000000007;
//                 }
//                 times <<= sel;
//                 times %= 1000000007;
//                 ans += (long long)(nums[j] - nums[i]) * times;
//                 ans %= 1000000007;
//             }
//         }
//         return ans % 1000000007;
//     }
// };

class Solution {
    const int MOD = 1e9 + 7;
public:
    int sumSubseqWidths(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size(), pow2[n];
        pow2[0] = 1;
        for (int i = 1; i < n; ++i)
            pow2[i] = pow2[i - 1] * 2 % MOD; // 预处理 2 的幂次
        long ans = 0L;
        for (int i = 0; i < n; ++i)
            ans += long(pow2[i] - pow2[n - 1 - i]) * nums[i]; // 在题目的数据范围下，这不会溢出
        return (ans % MOD + MOD) % MOD; // 注意上面有减法，ans 可能为负数
    }
};