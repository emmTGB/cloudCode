#include<bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* deleteMiddle(ListNode* h) {
        if(h->next == nullptr) return nullptr;
        ListNode* f = h, * s = h, * pre = nullptr;
        while (f && f->next) {
            f = f->next->next;
            pre = s;
            s = s->next;
        }
        pre->next = pre->next->next;
        return h;
    }
};