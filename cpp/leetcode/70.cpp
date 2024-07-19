#include<bits/stdc++.h>
#define ll long long
using namespace std;

class Solution {
public:
    vector<vector<int>> sandyLandManagement(int size) {
        vector<vector<int>> res(0);
        res.push_back({ 1,1 });
        for (int i = 2; i <= size; ++i) {
            switch ((size - i) % 4) {
            case 0:
                for (int j = 1; j <= i; ++j) {
                    res.push_back({ i, 2 * j - 1 });
                }
                break;
            case 1:
                res.push_back({ i, 2 });
                break;
            case 2:
                for (int j = 2; j <= i; ++j) {
                    res.push_back({ i, 2 * j - 1 });
                }
                break;
            case 3:
                res.push_back({ i, 1 });
                break;
            }
        }
        return res;
    }
};