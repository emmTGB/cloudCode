#include<bits/stdc++.h>
using namespace std;

struct Twt {
    int id;
    long long time;
    Twt* next;
};

struct ealier {
    bool operator() (Twt* t1, Twt* t2) {
        return t1->time < t2->time;
    }
};


class Twitter {
private:
    long long time;
    unordered_map<int, set<int>> follows;
    unordered_map<int, Twt*> twts;
public:
    Twitter() : time(0) {
    }

    void postTweet(int usr, int id) {
        Twt* t = new Twt(id, time, nullptr);
        if (twts.find(usr) != twts.end()) {
            t->next = twts[usr];
        }
        twts[usr] = t;
        ++time;
    }

    vector<int> getNewsFeed(int usr) {
        priority_queue<Twt*, vector<Twt*>, ealier> maxHeap;

        set<int> us = follows[usr];
        us.insert(usr);
        for (int u : us) {
            Twt* t = twts.find(u) == twts.end() ? nullptr : twts[u];
            while (t) {
                maxHeap.push(t);
                t = t->next;
            }
        }

        vector<int> res;
        while (!maxHeap.empty() && res.size() < 10) {
            res.push_back(maxHeap.top()->id);
            maxHeap.pop();
        }

        return res;
    }

    void follow(int er, int ee) {
        if (follows.find(er) == follows.end()) {
            follows[er] = set<int>();
        }
        follows[er].insert(ee);
    }

    void unfollow(int er, int ee) {
        if (follows.find(er) == follows.end()) {
            follows[er] = set<int>();
        }
        follows[er].erase(ee);
    }
};

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter* obj = new Twitter();
 * obj->postTweet(userId,tweetId);
 * vector<int> param_2 = obj->getNewsFeed(userId);
 * obj->follow(followerId,followeeId);
 * obj->unfollow(followerId,followeeId);
 */