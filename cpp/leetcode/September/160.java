import java.util.HashSet;
import java.util.Set;

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> v = new HashSet<>();
        ListNode tmp = headA;
        while(tmp != null){
            v.add(tmp);
            tmp = tmp.next;
        }
        tmp = headB;
        while(tmp != null){
            if(v.contains(tmp)){
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}