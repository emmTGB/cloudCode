class Solution {
public:
    int hammingWeight(int n) {
        int res = 0;
        while (n) {
            if (n & 1) res++;  // 和0x000001 做与运算，判断低位是否为1
            n >>= 1;
        }
        return res;
    }
};