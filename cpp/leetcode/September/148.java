class Solution {
    // 归并写法
    public ListNode sortList(ListNode h) {
        if (h == null || h.next == null)
            return h;
        ListNode m = middleNode(h);
        h = sortList(h);
        m = sortList(m);
        return meregeList(h, m);
    }

    public ListNode middleNode(ListNode h) {
        ListNode p = h, s = h, f = h;
        while (f != null && f.next != null) {
            p = s;
            s = s.next;
            f = f.next.next;
        }
        p.next = null;
        return s;
    }

    public ListNode meregeList(ListNode l, ListNode r) {
        ListNode h = new ListNode(-1), tmp = h;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                tmp.next = l;
                l = l.next;
                tmp = tmp.next;
            } else if (l.val > r.val) {
                tmp.next = r;
                r = r.next;
                tmp = tmp.next;
            }
        }
        tmp.next = l == null ? r : l;
        return h.next;
    }
}

class SolutionB {
    public ListNode sortList(ListNode head) {
        int length = getListLength(head); // 获取链表长度
        ListNode dummy = new ListNode(0, head); // 用哨兵节点简化代码逻辑
        // step 为步长，即参与合并的链表长度
        for (int step = 1; step < length; step *= 2) {
            ListNode newListTail = dummy; // 新链表的末尾
            ListNode cur = dummy.next; // 每轮循环的起始节点
            while (cur != null) {
                // 从 cur 开始，分割出两段长为 step 的链表，头节点分别为 head1 和 head2
                ListNode head1 = cur;
                ListNode head2 = splitList(head1, step);
                cur = splitList(head2, step); // 下一轮循环的起始节点
                // 合并两段长为 step 的链表
                ListNode[] merged = mergeTwoLists(head1, head2);
                // 合并后的头节点 merged[0]，插到 newListTail 的后面
                newListTail.next = merged[0];
                newListTail = merged[1]; // merged[1] 现在是新链表的末尾
            }
        }
        return dummy.next;
    }

    // 获取链表长度
    private int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // 分割链表
    // 如果链表长度 <= size，不做任何操作，返回空节点
    // 如果链表长度 > size，把链表的前 size 个节点分割出来（断开连接），并返回剩余链表的头节点
    private ListNode splitList(ListNode head, int size) {
        // 先找到 nextHead 的前一个节点
        ListNode cur = head;
        for (int i = 0; i < size - 1 && cur != null; i++) {
            cur = cur.next;
        }

        // 如果链表长度 <= size
        if (cur == null || cur.next == null) {
            return null; // 不做任何操作，返回空节点
        }

        ListNode nextHead = cur.next;
        cur.next = null; // 断开 nextHead 的前一个节点和 nextHead 的连接
        return nextHead;
    }

    // 21. 合并两个有序链表（双指针）
    // 返回合并后的链表的头节点和尾节点
    private ListNode[] mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        while (cur.next != null) {
            cur = cur.next;
        }
        // 循环结束后，cur 是合并后的链表的尾节点
        return new ListNode[] { dummy.next, cur };
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