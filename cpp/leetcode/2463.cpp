#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int candidates) {
        int n = costs.size();
        long long ans = 0;
        if (2 * candidates + k > n) {
            sort(costs.begin(), costs.end());
            for (int i = 0; i < k; i++) {
                ans += costs[i];
            }
            return ans;
        }
        else {
            priority_queue<int, vector<int>, greater<int>> pq1, pq2;
            for (int i = 0; i < candidates; i++) {
                pq1.push(costs[i]);
                pq2.push(costs[n - i - 1]);
            }

            int i = candidates, j = n - candidates - 1;
            while (k--) {
                if (pq1.top() > pq2.top()) {
                    ans += pq2.top();
                    pq2.pop();
                    pq2.push(costs[j--]);
                }
                else {  // 相同用左边
                    ans += pq1.top();
                    pq1.pop();
                    pq1.push(costs[i++]);
                }
            }
            return ans;
        }
    }
};