class Solution {
    public ListNode detectCycle(ListNode h) {
        if (h == null || h.next == null)
            return null;
        ListNode f = h, s = h;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (s == f) {
                ListNode t = h;
                while (s != t) {
                    s = s.next;
                    t = t.next;
                }
                return t;
            }
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
