
struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
    TreeNode* pre = nullptr;
public:
    TreeNode* convertBiNode(TreeNode* root) {
        if (!root)
            return nullptr;

        convertBiNode(root->right);
        root->right = pre;
        pre = root;
        convertBiNode(root->left);
        root->left = nullptr;
        return pre;
    }
};