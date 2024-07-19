#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countTime(string time) {
        int h, m;
        if (time[0] == '?') {
            if (time[1] == '?') h = 24;
            else if (time[1] - '4' >= 0) h = 2;
            else h = 3;
        }
        else {
            if (time[1] == '?') {
                if (time[0] == '2') h = 4;
                else h = 10;
            }
            else h = 1;
        }

        if (time[3] == '?') {
            if (time[4] == '?') m = 60;
            else m = 6;
        }
        else {
            if (time[4] == '?') m = 10;
            else m = 1;
        }
        return m * h;
    }
};