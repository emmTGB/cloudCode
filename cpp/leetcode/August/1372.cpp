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
    int res = 0;

    void dfs(TreeNode* node, bool isLeft, int length) {
        if (!node) return;
        res = max(res, length);
        if (isLeft) {
            dfs(node->left, false, length + 1);  // 下一步走右
            dfs(node->right, true, 1);            // 新路径，长度重置为1
        }
        else {
            dfs(node->right, true, length + 1);  // 下一步走左
            dfs(node->left, false, 1);            // 新路径，长度重置为1
        }
    }

public:
    int longestZigZag(TreeNode* root) {
        if (!root) return 0;
        dfs(root->left, false, 1);  // 从左子节点开始，方向是向左
        dfs(root->right, true, 1);  // 从右子节点开始，方向是向右
        return res;
    }
};