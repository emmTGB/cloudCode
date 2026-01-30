/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode sent = new ListNode(0, head), tmp = sent;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode sw = tmp.next.next;
            tmp.next.next = sw.next;
            sw.next = tmp.next;
            tmp.next = sw;
            tmp = tmp.next.next;
        }
        return sent.next;
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