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
    long long kthLargestLevelSum(TreeNode* root, int k) {
        vector<long long> v(1024, 0);
        function<void(TreeNode*, int)> dfs = [&](TreeNode* t, int dept) {
            if (t == nullptr) return;
            v[dept] += t->val;
            dfs(t->left, dept + 1);
            dfs(t->right, dept + 1);
            };
        dfs(root, 0);
        sort(v.begin(), v.end(), greater<long long>());
        return v[k - 1] == 0 ? -1 : v[k - 1];
    }
};