#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countServers(vector<vector<int>>& grid) {
        int total = 0;
        int off = 0;
        vector<int> rowCount(grid.size(), 0), colCount(grid[0].size(), 0);
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[0].size(); j++) {
                if (grid[i][j] == 1) {
                    total++;
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[0].size(); j++) {
                if (grid[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {
                    off++;
                }
            }
        }
        return total - off;
    }
};