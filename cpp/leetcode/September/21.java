class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode h = new ListNode(-1), tmp = h;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tmp.next = list1;
                tmp = tmp.next;
                list1 = list1.next;
            } else {
                tmp.next = list2;
                tmp = tmp.next;
                list2 = list2.next;
            }
        }
        tmp.next = list1 == null ? list2 : list1;
        return h.next;
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