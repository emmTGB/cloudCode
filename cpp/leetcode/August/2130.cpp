
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
    int pairSum(ListNode* h) {
        int res = INT_MIN;
        ListNode* f = h->next, * s = h;
        if (f->next == nullptr) return f->val + s->val;
        vector<int> a;
        while (f && f->next) {
            f = f->next->next;
            a.push_back(s->val);
            s = s->next;
        }
        a.push_back(s->val);
        s = s->next;
        int i = a.size();
        while (s) {
            res = max(res, s->val + a[--i]);
            s = s->next;
        }
        return res;
    }
};

// 反转列表
class BetterSolution {
public:
    int pairSum(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head->next;
        while (fast->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        // 反转链表
        ListNode* last = slow->next;
        while (last->next) {
            ListNode* cur = last->next;
            last->next = cur->next;
            cur->next = slow->next;
            slow->next = cur;
        }
        int ans = 0;
        ListNode* x = head;
        ListNode* y = slow->next;
        while (y) {
            ans = max(ans, x->val + y->val);
            x = x->next;
            y = y->next;
        }
        return ans;
    }
};
