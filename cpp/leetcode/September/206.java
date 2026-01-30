class Solution {
    public ListNode reverseList(ListNode h) {
        ListNode tmp = null, t = null;
        while (h != null) {
            t = h.next;
            h.next = tmp;
            tmp = h;
            h = t;
        }
        return tmp;
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
