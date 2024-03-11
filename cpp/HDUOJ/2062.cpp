//ac
//2062 DP

#include<iostream>
#include<cmath>

int main() {
    int n;
    long long m;

    long long c[25] = { 0 };
    for (int i = 1; i < 25; i++) {
        c[i] = c[i - 1] * (i - 1) + 1;  // n 元素集的有序子集分为 n 组，每组子集个数存放于此
    }

    int res[25];

    while (std::cin >> n >> m) {

        for (int i = 0; i < 25; i++) {
            res[i] = i;
        }

        while (n && m > 0) {
            int k = (m - 1) / c[n] + 1;  // 确定当前层级的组数所对应的元素序号
            std::cout << res[k] << " ";
            for (int i = k; i < 25; ++i) {
                res[i] = res[i + 1];  // 删除使用过的元素
            }
            m -= (k - 1) * c[n] + 1;  // 更新m为余下的子集序号
            --n;
        }

        std::cout << std::endl;
    }
}