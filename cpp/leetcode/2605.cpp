#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int minNumber(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        int n1 = nums1[0], n2 = nums2[0];
        if (n1 > n2) swap(n1, n2);
        if (nums1.size() > nums2.size()) swap(nums1, nums2);
        for (int i = 0; i < nums2.size(); i++) {
            if (binary_search(nums1.begin(), nums1.end(), nums2[i])) {
                return nums2[i];
            }
        }
        return n1 * 10 + n2;
    }
};