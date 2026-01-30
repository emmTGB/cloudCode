#include<bits/stdc++.h>
using namespace std;
#define ll long long

class Solution {
public:
    int findMinMoves(vector<int>& machines) {
        ll total = 0;
        int n = machines.size();
        for (auto i : machines) {
            total += i;
        }
        if (total % n) return -1;
        ll avg = total / n;
        int step = 0;
        ll ls = 0, rs = total;
        for (int i = 0; i < n; ++i) {
            rs -= machines[i];
            int a = max(0, int(i * avg - ls));
            int b = max(0, int((n - i - 1) * avg - rs));
            step = max(step, a + b);
            ls += machines[i];
        }
        return step;
    }
};

int main() {
    Solution s;
    vector<int> v = { 7, 6, 5, 4, 3, 2, 1 };
    cout << s.findMinMoves(v);
}