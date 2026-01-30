#include<bits/stdc++.h>
using namespace std;


// 广度优先带筛，我就要开你别管我
class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& r) {
        int n = r.size();
        queue<int> keys;
        set<int> s;
        s.insert(0);
        keys.push(0);
        while (!keys.empty()) {
            int key = keys.front();
            keys.pop();
            s.insert(key);
            for (int room : r[key]) {
                if (!s.count(room))
                    keys.push(room);
            }
        }
        return s.size() == n;
    }
};