#include<bits/stdc++.h>
using namespace std;

class RecentCounter {
    queue<int> q;
public:
    RecentCounter() {
    }

    int ping(int t) {
        q.push(t);
        while (q.front() + 3000 < t) {
            q.pop();
        }

        return q.size();
    }
};