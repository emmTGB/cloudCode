//ac
//1087 DP

#include<iostream>
#include<vector>
#include<algorithm>

int main() {
    int n;
    while (std::cin >> n && n != 0) {
        int c[n];
        std::vector<long long> dp(n, 0);  // dp 动态解数组 存储前 i 个棋子的最优解
        for (int i = 0; i < n; i++) {
            std::cin >> c[i];
        }

        dp[0] = c[0];
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (c[j] < c[i]) {
                    dp[i] = std::max(dp[i], dp[j] + c[i]);
                }
            }
            if (c[i] > dp[i])
                dp[i] = c[i];
        }
        std::cout << *std::max_element(dp.begin(), dp.end()) << std::endl;
    }
}
