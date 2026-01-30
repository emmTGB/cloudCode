class Solution {
    public boolean hasCycle(ListNode h) {
        if(h == null || h.next == null) return false;
        ListNode f = h, s = h;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (s == f) {
                return true;
            }
        }
        return false;
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
