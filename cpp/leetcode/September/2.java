class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l, r, c = 0;
        ListNode h = l1, t = l1;
        while (l1 != null && l2 != null) {
            t = l1;
            l = l1.val;
            r = l2.val;
            int x = l + r + c;
            c = x / 10;
            x %= 10;
            l1.val = x;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && t != null)
            t.next = l2;
        while (c != 0) {
            if (t.next == null) {
                t.next = new ListNode(0, null);
            }
            int x = t.next.val + c;
            c = x / 10;
            x %= 10;
            t.next.val = x;
            t = t.next;
        }
        return h;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}