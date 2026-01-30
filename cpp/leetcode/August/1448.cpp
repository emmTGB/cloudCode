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
    int goodNodes(TreeNode* root) {
        return gn(root, INT32_MIN);
    }

    int gn(TreeNode* r, int m) {
        if (!r) return 0;
        int tr = m <= r->val, n = max(m, r->val);
        int l = gn(r->left, n);
        int ri = gn(r->right, n);
        return tr + l + ri;
    }
};