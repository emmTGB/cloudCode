//
//1203 DP

#include<bits/stdc++.h>

int main() {
    int m, n;
    std::cin >> m >> n;

    std::vector<std::pair<int, double>> v(n);
    std::vector<std::pair<int, double>> dp(n + 1, std::make_pair(0, 1));

    for (int i = 0; i < n; i++) {
        int a; double p;
        std::cin >> a >> p;
        v[i] = std::make_pair(a, 1 - p);
        for (int j = 0; j <= i; ++j) {
            if (dp[j].first + v[i].first <= m) {
                if (dp[j].second * v[i].second < dp[i + 1].second) {
                    dp[i + 1] = std::make_pair(dp[j].first + v[i].first, dp[j].second * v[i].second);
                }
            }
            if (i != j && v[j].first + v[i].first <= m && v[j].second * v[i].second < dp[i + 1].second) {
                dp[i + 1] = std::make_pair(v[j].first + v[i].first, v[j].second * v[i].second);
            }
        }
    }

    std::cout << std::fixed << std::setprecision(1) << (1 - dp[n].second) * 100 << "%" << std::endl;
}