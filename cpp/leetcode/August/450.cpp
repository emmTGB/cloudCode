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
    TreeNode* deleteNode(TreeNode* r, int key) {
        if (!r) return r;
        if (key < r->val) {
            r->left = deleteNode(r->left, key);
        }
        else if (key > r->val) {
            r->right = deleteNode(r->right, key);
        }
        else {
            if (!r->left) return r->right;
            if (!r->right) return r->left;
            TreeNode* tmp = r->right;
            while (tmp->left) {
                tmp = tmp->left;
            }
            r->val = tmp->val;
            r->right = deleteNode(r->right, tmp->val);
        }
        return r;
    }
};