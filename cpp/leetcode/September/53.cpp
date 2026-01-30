#include<bits/stdc++.h>
using namespace std;
//dp
class Solution {
public:
    int maxSubArray(vector<int>& n) {
        int f = INT_MIN, ans = INT_MIN;
        for (int x : n) {
            f = max(f, 0) + x;
            ans = max(f, ans);
        }
        return ans;
    }
};

//  dp
/*

f(i) 表示以第i位结尾的最大子数组的和

f(i)={
    n[i],   i == 0;
    max(f(i - 1) , 0) + n[i],  i > 0;
}

ans = max(f(i));

*/