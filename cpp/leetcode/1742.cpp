#include<bits/stdc++.h>
using namespace std;

class Solution{
    private:
    vector<int> box = vector<int>(50);
    public:
    int countBalls(int lowLimit, int highLimit){
        int ans = 0;
        for(int i = lowLimit; i <= highLimit; ++i){
            int j = i, cur = 0;
            while(j){
                cur += j % 10;
                j /= 10;
            }
            if(++box[cur] > ans) ans = box[cur];
        }
        return ans;
    }
};