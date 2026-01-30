import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private class Node {
        public int key, val;
        public Node pre, next;

        Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    private int cap;

    private Node sent;
    private Map<Integer, Node> m = new HashMap<>();

    public LRUCache(int capacity) {
        sent = new Node(-1, -1);
        sent.pre = sent;
        sent.next = sent;
        cap = capacity > 0 ? capacity : 1;
    }

    public int get(int key) {
        Node n = getNode(key);
        return n == null ? -1 : n.val;
    }

    private Node getNode(int key) {
        if (!m.containsKey(key))
            return null;
        Node x = m.get(key);
        del(x);
        push(x);
        return x;
    }

    private void del(Node x) {
        x.pre.next = x.next;
        x.next.pre = x.pre;
    }

    private void push(Node x) {
        sent.next.pre = x;
        x.next = sent.next;
        x.pre = sent;
        sent.next = x;
    }

    public void put(int key, int value) {
        Node n = getNode(key);
        if (n != null) {
            n.val = value;
            return;
        }
        n = new Node(key, value);
        m.put(key, n);
        push(n);
        if (m.size() > cap) {
            Node b = sent.pre;
            m.remove(b.key);
            del(b);
        }
    }
}