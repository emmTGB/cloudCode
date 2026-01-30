class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode rear, front, sent = new ListNode(0, head);
        rear = sent;
        for (int i = 0; i < n; i++) {
            rear = rear.next;
        }
        front = sent;
        while (rear.next != null) {
            front = front.next;
            rear = rear.next;
        }
        front.next = front.next.next;
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