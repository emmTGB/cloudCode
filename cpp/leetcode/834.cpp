#include<iostream>
#include<vector>
#include <functional>
using namespace std;
// 树状dp
class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
            g[e[1]].push_back(e[0]);
        }
        vector<int> sln(n, 0), size(n, 1);
        function<void(int, int, int)> sizeDFS = [&](int x, int f, int d) {
            sln[0] += d;
            for (int v : g[x]) {
                if (v != f) {
                    sizeDFS(v, x, d + 1);
                    size[x] += size[v];
                }
            }
            };

        function<void(int, int)> slnDFS = [&](int x, int f) {
            for (int v : g[x]) {
                if (v != f) {
                    sln[v] = sln[x] + n - 2 * size[v];
                    slnDFS(v, x);
                }
            }
            };

        sizeDFS(0, -1, 0);
        slnDFS(0, -1);
        return sln;
    }
};