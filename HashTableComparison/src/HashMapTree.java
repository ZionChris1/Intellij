

public class HashMapTree<K, V> {
    private BinaryTree<Node>[] data;

    public HashMapTree() {
        data = new BinaryTree[10];
    }

    public HashMapTree(int size) {
        data = new BinaryTree[size];
    }

    public void add(K key, V value) {
        Node pair = new Node(key, value);
        int hash = Math.abs(key.hashCode() % data.length);
        BinaryTree<Node> tree = data[hash];

        if(tree == null)
            data[hash] = new BinaryTree(new Node(key, value), null, null);
        else {
            while (tree != null) {
                Node node = tree.getData();
                if(key.hashCode() < node.key.hashCode()) {
                    if(tree.getLeftSubtree() != null)
                        tree = tree.getLeftSubtree();
                    else
                        tree.setLeft(pair);
                } else if(key.hashCode() < node.key.hashCode()) {
                    if(tree.getRightSubtree() != null)
                        tree = tree.getRightSubtree();
                    else
                        tree.setRight(pair);
                } else { //Already added
                    return;
                }
            }
        }

    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % data.length);
        BinaryTree tree = data[hash];
        while (!tree.isLeaf()) {
            Node node = (Node) tree.getData();
            if (key.equals(node.key))
                return node.value;

            if(tree.isLeaf())
                break;

            int treehash = ((Node)tree.getData()).key.hashCode();
            if(hash < treehash)
                tree = tree.getLeftSubtree();
            else
                tree = tree.getRightSubtree();
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