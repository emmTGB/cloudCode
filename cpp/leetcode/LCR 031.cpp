#include<bits/stdc++.h>
using namespace std;

class QNode {
public:
    int key, value;
    QNode* prev, * next;
    QNode(int k = 0, int v = 0) : key(k), value(v) {}
};

class LRUCache {
private:
    int cap = 0;
    QNode* sentry;
    unordered_map<int, QNode*> kn;

    void rm(QNode* n) {
        n->prev->next = n->next;
        n->next->prev = n->prev;
    }

    void push(QNode* n) {
        n->prev = sentry;
        n->next = sentry->next;
        n->prev->next = n;
        n->next->prev = n;
    }

    QNode* get_n(int key) {
        auto it = kn.find(key);
        if (it == kn.end()) {
            return nullptr;
        }
        auto node = it->second;
        rm(node);
        push(node);
        return node;
    }

public:
    LRUCache(int capacity) {
        cap = capacity;
        sentry = new QNode();
        sentry->next = sentry;
        sentry->prev = sentry;
    }

    int get(int key) {
        auto node = get_n(key);
        return node ? node->value : -1;
    }

    void put(int key, int value) {
        auto node = get_n(key);
        if (node) {
            node->value = value;
            return;
        }
        kn[key] = node = new QNode(key, value);
        push(node);
        if (kn.size() > cap) {
            auto back = sentry->prev;
            kn.erase(back->key);
            rm(back);
            delete back;
        }
    }
};
