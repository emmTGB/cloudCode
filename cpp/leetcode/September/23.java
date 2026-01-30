import java.util.PriorityQueue;

class Solution {
    // 小顶堆
    public ListNode mergeKLists(ListNode[] ls) {
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode l : ls) {
            if (l != null)
                q.offer(l);
        }

        ListNode sent = new ListNode(-1);
        ListNode cur = sent;
        while (!q.isEmpty()) {
            ListNode n = q.poll();
            if (n.next != null) {
                q.offer(n.next);
            }
            cur.next = n;
            cur = cur.next;
        }

        return sent.next;
    }
}
