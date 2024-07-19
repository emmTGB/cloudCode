
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
    bool isSymmetric(TreeNode* root) {
        TreeNode* l = root->left, * r = root->right;
        return ss(l, r);
    }

    bool ss(TreeNode* l, TreeNode* r) {
        if (l == nullptr && r == nullptr) return true;
        else if (l == nullptr || r == nullptr) return false;
        if (l->val != r->val) return false;
        return ss(l->left, r->right) && ss(l->right, r->left);
    }
};