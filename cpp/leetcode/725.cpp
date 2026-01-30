#include<bits/stdc++.h>
using namespace std;


struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    vector<ListNode*> splitListToParts(ListNode* head, int k) {
        int grp = 0, rem = 0;
        ListNode* temp = head;
        while(temp){
            grp++;
            temp = temp->next;
        }
        rem = grp%k;
        grp /= k;
        temp = head;
        vector<ListNode*> ans;
        int cnt = 0;
        ans.push_back(temp);
        while(temp){
            cnt++;
            if(cnt == grp + (rem > 0)){
                ListNode* t = temp->next;
                temp->next = NULL;
                temp = t;
                cnt = 0;
                rem--;
                if(temp)
                ans.push_back(temp);
            }else
            temp = temp->next;
        }
        while(ans.size() < k){
            ans.push_back(NULL);
        }
        return ans;
    }
};