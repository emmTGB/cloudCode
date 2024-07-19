#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int pivotInteger(int n) {
        if(n == 1)
            return 1;

        double tmp = sqrt((n*n+n)/2);
        int res = ceil(tmp);
        if(res*res == (n*n+n)/2)
            return res;
        else
            return -1;
    }
};