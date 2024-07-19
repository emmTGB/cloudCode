#include<bits/stdc++.h>
using namespace std;

// 贪心
class Solution {
public:
    int minimumPartition(string s, int k) {
        int ans = 1;
        long long num = 0;
        for (int i = 0;i < s.size();i++)
        {
            if (s[i] - '0' > k) return -1;
            num = num * 10 + (s[i] - '0');
            if (num > k)
            {
                num = s[i] - '0';
                ans++;
            }
        }
        return ans;
    }
};
int main() {
    string s = "165462";
    int k = 60;
    Solution sol;
    cout << sol.minimumPartition(s, k);
}