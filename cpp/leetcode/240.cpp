#include<bits/stdc++.h>
using namespace std;

class Solution {
    int target;
    bool find(vector<vector<int>>& matrix, int x1, int y1, int x2, int y2) {
        if (x2 <= x1 || y2 <= y1) return false;
        int l = x2 - x1, h = y2 - y1;
        if (l == 1 && h == 1) return matrix[x1][y1] == target;
        int x = x1 + l / 2, y = y1 + h / 2;
        if (matrix[x][y] == target)return true;
        else if (matrix[x][y] > target)
            return (
                find(matrix, x + 1, y + 1, x2, y2)
                || find(matrix, x1, y + 1, x + 1, y2)
                || find(matrix, x + 1, y1, x2, y + 1)
                );
        else
            return(
                find(matrix, x1, y1, x, y)
                || find(matrix, x1, y, x, y2)
                || find(matrix, x, y1, x2, y)
            );
        
        return false;
    }
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        this->target = target;
        int m = matrix.size(), n = matrix[0].size();
        return find(matrix, 0, 0, m, n);
    }
};