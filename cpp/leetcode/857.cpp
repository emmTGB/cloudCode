#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    double mincostToHireWorkers(vector<int>& quality, vector<int>& wage, int k) {
        int n = quality.size(), sumQ = 0;
        vector<int> id(n);
        iota(id.begin(), id.end(), 0);
        ranges::sort(id, [&](int i, int j) {
            return wage[i] * quality[j] < wage[j] * quality[i];
            });
        priority_queue<int> pq;
        for (int i = 0; i < k; ++i) {
            pq.push(quality[id[i]]);
            sumQ += quality[id[i]];
        }
        double ans = sumQ * ((double)wage[id[k - 1]] / quality[id[k - 1]]);
        for (int i = k; i < n; ++i) {
            int q = quality[id[i]];
            if (q < pq.top()) {
                sumQ -= pq.top() - q;
                pq.pop();
                pq.push(q);
                ans = min(ans, sumQ * ((double)wage[id[i]] / q));
            }
        }
        return ans;
    }
};