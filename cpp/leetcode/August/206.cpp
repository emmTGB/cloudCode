

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
    ListNode* reverseList(ListNode* h) {
        ListNode* r = nullptr;
        while (h) {
            ListNode* n = h->next;
            h->next = r;
            r = h;
            h = n;
        }
        return r;
    }
};