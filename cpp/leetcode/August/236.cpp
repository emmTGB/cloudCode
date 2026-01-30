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
    TreeNode* ans = nullptr;
public:
    TreeNode* lowestCommonAncestor(TreeNode* r, TreeNode* p, TreeNode* q) {
        dfs(r, p->val, q->val);
        return ans;
    }

    int dfs(TreeNode* n, int p, int q) {
        if (!n) return 0;
        int flag = dfs(n->left, p, q) | dfs(n->right, p, q);
        if (n->val == p) flag |= 1;
        if (n->val == q) flag |= 2;
        if (flag == 3 && !ans) ans = n;
        return flag;
    }
};