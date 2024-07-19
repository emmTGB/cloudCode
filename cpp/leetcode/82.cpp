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
    ListNode* deleteDuplicates(ListNode* head) {
        if (!head) return head;
        ListNode* pre = new ListNode(0, head), * ptr = head->next;
        int now = head->val;
        bool flag = false;
        head = pre;
        while (ptr) {
            if (ptr->val == now) {
                flag = true;
            }
            else {
                now = ptr->val;
                if (flag) {
                    head->next = ptr;
                }
                else {
                    head = head->next;
                }
                flag = false;
            }
            ptr = ptr->next;
        }
        if (flag) head->next = nullptr;

        return pre->next;
    }
};