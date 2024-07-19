

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class BSTIterator {
private:
    TreeNode* r, * now;
public:
    BSTIterator(TreeNode* root) {
        r = new TreeNode(-1);
        now = r;
        travel(root);
    }

    int next() {
        r = r->left;
        return r->val;
    }

    bool hasNext() {
        return  r != now;
    }

    void travel(TreeNode* node) {
        if (!node) return;
        travel(node->left);
        now->left = node;
        now = node;
        travel(node->right);
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */