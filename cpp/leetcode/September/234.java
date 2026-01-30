class Solution {
    public boolean isPalindrome(ListNode h) {
        if (h.next == null)
            return true;

        ListNode f = h, s = h;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        ListNode nh = reverseList(s);
        while(nh != null){
            if(h.val != nh.val){
                return false;
            }
            h = h.next;
            nh = nh.next;
        }
        return true;
    }

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
