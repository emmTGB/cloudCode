#include<bits/stdc++.h>
using namespace std;


struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode* left, TreeNode* right) : val(x), left(left), right(right) {}
};


class Solution {
public:
    int pathSum(TreeNode* root, int targetSum) {
        int ans = 0;
        unordered_map<long long, int> mp{ {0, 1} };// 用于存储路径前缀和
        auto dfs = [&](this auto&& dfs, TreeNode* r, long long s) {
            if (r == nullptr) return;

            s += r->val;

            ans += mp[s - targetSum];  // 查询是否存在前缀和使得去掉对应路径后sum等于target
            mp[s]++;
            dfs(r->left, s);
            dfs(r->right, s);
            mp[s]--;
            };
        dfs(root, 0);
        return ans;
    }
};