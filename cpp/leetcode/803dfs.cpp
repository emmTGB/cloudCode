#include<bits/stdc++.h>
using namespace std;

class Solution {
    vector<pair<int, int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    bool isStable(int x, int y, vector<vector<int>>& grid){
        if(x==0) return true;
        for(auto& dir:dirs){
            if(x + dir.first >= 0 && y + dir.second >= 0
            && x + dir.first < grid.size() && y + dir.second < grid[0].size())
            if(grid[x+dir.first][y+dir.second]==2) return true;
        }
        return false;
    }
public:
    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        for(auto &i:hits){
            grid[i[0]][i[1]]--;
        }

        function<int(int, int)> dfs =[&](int x, int y)-> int {
            if(x>=0 && x<grid.size() && y>=0 && y<grid[0].size() && grid[x][y]==1){
                grid[x][y] = 2;
                return 1 + dfs(x+1, y) + dfs(x-1, y) + dfs(x, y+1) + dfs(x, y-1);
            }
            return 0;
        };

        for(int i=0;i<grid[0].size();i++){
            dfs(0, i);
        }

        vector<int> res(hits.size(), 0);
        for(int i = hits.size()-1;i>=0;i--){
            auto &h = hits[i];
            int x = h[0], y = h[1];
            grid[x][y]++;
            if(grid[x][y]!=1 || !isStable(x, y, grid)){
                continue;
            }else{
                res[i] = dfs(x, y) - 1;
            }
        }
        return res;
    }
};