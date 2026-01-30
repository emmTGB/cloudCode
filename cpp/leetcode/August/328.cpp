

struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};


#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    ListNode* oddEvenList(ListNode* h) {
        if (!h) return nullptr;
        ListNode* eh = h->next;
        ListNode* o = h, * d = eh;
        while (d && d->next) {
            o->next = d->next;
            o = d->next;
            d->next = o->next;
            d = d->next;
        }
        o->next = eh;
        return h;
    }
};