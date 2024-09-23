#include<bits/stdc++.h>
using namespace std;

class CountIntervals {
    map<int, int> mp;
    int cnt = 0;
public:
    CountIntervals() {

    }

    void add(int left, int right) {
        for(auto it = mp.lower_bound(left); it != mp.end() && it->second <= right; mp.erase(it++)){
            int l = it->second, r = it->first;
            left = min(left, l);
            right = max(right, r);
            cnt -= r - l + 1;
        }
        cnt += right - left + 1;
        mp[right] = left;
    }

    int count() {
        return cnt;
    }
};

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals* obj = new CountIntervals();
 * obj->add(left,right);
 * int param_2 = obj->count();
 */