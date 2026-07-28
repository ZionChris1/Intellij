import java.util.LinkedList;
import java.util.ListIterator;

public class HashMapOpenChain<K, V> {
    private LinkedList[] data;

    public HashMapOpenChain() {
        data = new LinkedList[10];
    }

    public HashMapOpenChain(int size) {
        data = new LinkedList[size];
    }

    public void add(K key, V value) {
        Node pair = new Node(key, value);
        int hash = Math.abs(key.hashCode() % data.length);
        if (data[hash] == null)
            data[hash] = new LinkedList<Node>();
        data[hash].add(pair);
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % data.length);
        ListIterator li = data[hash].listIterator();
        while (li.hasNext()) {
            Node node = (Node) li.next();
            if (key.equals(node.key))
                return node.value;
        }
        return null;
    }

    private class Node {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}