//
//1203 DP

#include<bits/stdc++.h>

int main() {
    int m, n;
    std::cin >> m >> n;

    std::vector<std::pair<int, double>> v(n);
    std::vector<std::pair<int, double>> dp(n + 1, std::make_pair(0, 0));

    for (int i = 0;i < n;i++) {
        int a; double p;
        std::cin >> a >> p;
        v[i] = std::make_pair(a, p);
        for (int j = 0; j <= i; ++j) {
            if (dp[j].first + dp[i + 1].first <= m){
                
            }
        }
    }
}