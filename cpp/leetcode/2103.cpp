#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countPoints(string rings) {
        int r[10] = { 0 };
        int ans = 0;
        for (int i = 1; i < rings.size(); i += 2) {
            int c;
            if (rings[i - 1] == 'R') c = 1;
            if (rings[i - 1] == 'G') c = 2;
            if (rings[i - 1] == 'B') c = 4;
            r[rings[i] - '0'] |= c;
        }
        for(int i = 0; i < 10; i++){
            if(r[i] == 7) ans++;
        }
        return ans;
    }
};