#include<bits/stdc++.h>
using namespace std;


// 广搜写法，写法简单但是效率略低
class Solution {
public:
    int findCircleNum(vector<vector<int>>& c) {
        int n = c.size();
        int ans = 0;
        queue<int> q;
        vector<int> s(n, 0);
        for (int i = 0; i < c.size(); ++i) {
            if (!s[i]) {
                q.push(i);
                while (!q.empty()) {
                    int f = q.front();
                    s[f] = 1;
                    q.pop();
                    for (int j = 0; j < n; ++j) {
                        if (c[f][j] && !s[j])
                            q.push(j);
                    }
                }
                ++ans;
            }
        }
        return ans;
    }
};


/*  JAVA  并查集写法

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        // 初始化并查集
        UnionFind uf = new UnionFind(n);
        // 遍历每个顶点，将当前顶点与其邻接点进行合并
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        // 返回最终合并后的集合的数量
        return uf.size;
    }
}  

// 并查集
class UnionFind {
    int[] roots;
    int size; // 集合数量

    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        size = n;
    }

    public int find(int i) {
       if (i == roots[i]) {
           return i;
       }
       return roots[i] = find(roots[i]);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            roots[pRoot] = qRoot;
            size--;
        }
    }
}

*/