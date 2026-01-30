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
    vector<int> rightSideView(TreeNode* r) {
        vector<int> res;
        auto dfs = [&](this auto&& dfs, TreeNode* r, int depth) {
            if (!r) return;
            if (depth == res.size()) {
                res.push_back(r->val);
            }
            dfs(r->right, depth + 1);
            dfs(r->left, depth + 1);
            };
        
            dfs(r, 0);
        return res;
    }
};