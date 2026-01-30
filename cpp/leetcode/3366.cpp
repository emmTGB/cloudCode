#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minArraySum(vector<int>& nums, int k, int op1, int op2) {
        ranges::sort(nums);
        int high = ranges::lower_bound(nums, k * 2 - 1) - nums.begin();
        int low = ranges::lower_bound(nums, k) - nums.begin();

        // 在 [2k-1,∞) 中的数，直接先除再减（从大到小操作）
        for (int i = nums.size() - 1; i >= high; i--) {
            if (op1) {
                nums[i] = (nums[i] + 1) / 2;
                op1--;
            }
            if (op2) {
                nums[i] -= k;
                op2--;
            }
        }

        // 在 [k,2k-2] 中的数，先把小的数 -k
        unordered_multiset<int> st;
        int odd = 0;
        for (int i = low; i < high; i++) {
            if (op2) {
                nums[i] -= k;
                if (k % 2 && nums[i] % 2) {
                    // nums[i] 原来是偶数，后面有机会把这次 -k 操作留给奇数，得到更小的答案
                    st.insert(nums[i]);
                }
                op2--;
            }
            else {
                odd += nums[i] % 2; // 没有执行 -k 的奇数
            }
        }

        // 重新排序（注：这里可以改用合并两个有序数组的做法）
        sort(nums.begin(), nums.begin() + high);

        int ans = 0;
        if (k % 2) {
            // 调整，对于 [k,2k-2] 中 -k 后还要再 /2 的数，如果原来是偶数，改成给奇数 -k 再 /2，这样答案可以减一
            for (int i = high - op1; i < high && odd; i++) {
                int x = nums[i];
                auto it = st.find(x);
                if (it != st.end()) {
                    st.erase(it);
                    odd--;
                    ans--;
                }
            }
        }

        // 最后，从大到小执行操作 1
        for (int i = high - 1; i >= 0 && op1; i--) {
            nums[i] = (nums[i] + 1) / 2;
            op1--;
        }

        return ans + reduce(nums.begin(), nums.end(), 0);
    }
};


int main() {
    Solution s;
    vector<int> v({ 3 });
    cout << s.minArraySum(v, 6, 0, 1);
}