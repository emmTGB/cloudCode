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

    void getLeafs(TreeNode* r, vector<int>& l) {
        if (!r) return;
        if (!(r->left || r->right)) {
            l.push_back(r->val);
            return;
        }
        getLeafs(r->left, l);
        getLeafs(r->right, l);
    }

    bool leafSimilar(TreeNode* r1, TreeNode* r2) {
        vector<int> l1, l2;
        getLeafs(r1, l1);
        getLeafs(r2, l2);
        return l1 == l2;
    }
};