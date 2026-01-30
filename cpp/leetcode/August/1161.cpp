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
    int maxLevelSum(TreeNode* r) {
        int m = INT32_MIN;
        queue<TreeNode*> q;
        q.push(r);
        int level = 1, sum = 0, ans = 1;
        while (!q.empty()) {
            int now = q.size();
            for (int i = 0; i < now; ++i) {
                TreeNode* tmp = q.front();
                q.pop();
                sum += tmp->val;
                if (tmp->left) q.push(tmp->left);
                if (tmp->right) q.push(tmp->right);
            }
            if (sum > m) {
                m = sum;
                ans = level;
            }
            sum = 0;
            ++level;
        }
        return ans;
    }
};