#include<bits/stdc++.h>
using namespace std;

class UnionFind {
private:
    unordered_map<int, int> father;
    unordered_map<int, int> size_of_set;

public:
    int find(int x) {
        int root = x;

        while (father[root] != INT_MAX) {
            root = father[root];
        }
        // 路径压缩
        while (x != root) {
            int original_father = father[x];
            father[x] = root;
            x = original_father;
        }

        return root;
    }

    int get_size_of_set(int x) {
        // 获取所在连通块的大小
        return size_of_set[find(x)];
    }

    bool is_connected(int x, int y) {
        return find(x) == find(y);
    }

    void merge(int x, int y) {
        auto root_x = find(x);
        auto root_y = find(y);

        if (root_x != root_y) {
            father[root_x] = root_y;
            // 更新根节点连通块的大小
            size_of_set[root_y] += size_of_set[root_x];
            size_of_set.erase(root_x);
        }
    }

    void add(int x) {
        if (!father.count(x)) {
            father[x] = INT_MAX;
            size_of_set[x] = 1;
        }
    }
};

class Solution {
private:
    int CEILING = -1;
    vector<pair<int, int>> DIRECTIONS = { {1,0},{-1,0},{0,1},{0,-1} };

public:
    void initialize(UnionFind& uf, vector<vector<int>>& grid, const vector<vector<int>>& hits, const int& m, const int& n) {
        /* 初始化 */
        // 添加天花板
        uf.add(CEILING);

        // 预先敲掉所有要敲掉的砖块
        for (int i = 0;i < hits.size();i++) {
            grid[hits[i][0]][hits[i][1]]--;
        }

        // 连接，合并剩余的没有被敲掉的砖块
        for (int i = 0;i < m;i++)
            for (int j = 0;j < n;j++)
                if (grid[i][j] == 1) {
                    uf.add(i * n + j);
                }

        for (int i = 0;i < m;i++)
            for (int j = 0;j < n;j++)
                if (grid[i][j] == 1) {
                    merge_neighbors(uf, grid, i, j, m, n);
                }

        // 第0行的砖与天花板合并
        for (int j = 0;j < n;j++)
            if (grid[0][j] == 1)
                uf.merge(j, CEILING);
    }

    bool is_valid(const int& i, const int& j, vector<vector<int>>& grid, const int& m, const int& n) {
        return 0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1;
    }

    void merge_neighbors(UnionFind& uf, vector<vector<int>>& grid, const int& i, const int& j, const int& m, const int& n) {
        /* 与上下左右的砖块合并 */
        for (int k = 0;k < 4;k++) {
            int new_x = i + DIRECTIONS[k].first;
            int new_y = j + DIRECTIONS[k].second;
            if (is_valid(new_x, new_y, grid, m, n)) {
                uf.merge(new_x * n + new_y, i * n + j);
            }
        }
    }


    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        UnionFind uf;
        int m = grid.size(), n = grid[0].size();

        // 初始化
        initialize(uf, grid, hits, m, n);

        vector<int> res(hits.size(), 0);

        for (int i = hits.size() - 1;i >= 0;i--) {
            int x = hits[i][0], y = hits[i][1];

            // 还原敲击
            if (++grid[x][y] != 1)
                continue;

            // 敲完后与天花板连接的数量
            int after_hit = uf.get_size_of_set(CEILING);

            // 填回砖块，与邻居合并
            uf.add(x * n + y);
            merge_neighbors(uf, grid, x, y, m, n);


            // 如果被敲掉的地方和天花板连接
            if (x == 0) {
                uf.merge(y, CEILING);
            }

            if (uf.is_connected(x * n + y, CEILING)) {
                // 敲之前和天花板连接的数量
                int before_hit = uf.get_size_of_set(CEILING);

                res[i] = before_hit - after_hit - 1;
            }
        }

        return res;
    }
};
