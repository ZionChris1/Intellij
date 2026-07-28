import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;

/**
 * BinaryTree.java:
 *
 * @param <E> data type of the tree.
 **
 * @author
 * @version 1.0
 */
public class BinaryTree<E> {

    protected Node<E> root;

    /**
     * Default constructor
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructor, assigns the node to the root of the tree.
     *
     * @param root node being assigned to the root.
     */
    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Tree constructor, builds a tree by adding two existing trees root node to the
     * left and right nodes of a new root node. The root node is created from the
     * data passed
     * to method.
     *
     * @param data      to be assigned to the root node.
     * @param leftTree  tree being added to left side of the tree.
     * @param rightTree tree being added to the right side of the tree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);

        if (leftTree == null)
            root.left = null;
        else
            root.left = leftTree.root;

        if (rightTree == null)
            root.right = null;
        else
            root.right = rightTree.root;
    }

    /**
     * Returns the subtree to the left of the root. Returns null if the tree is
     * empty
     * or there is no left subtree.
     *
     * @return the left subtree of the root node.
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null)
            return new BinaryTree(root.left);
        return null;
    }

    /**
     * Returns the subtree to the right of the root. Returns null if the tree is
     * empty
     * or there is no right subtree.
     *
     * @return the right subtree if the root node.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null)
            return new BinaryTree(root.right);
        return null;
    }

    /**
     * Checks to see if the current node is a leaf.
     *
     * @return boolean if the node is a leaf.
     */
    public boolean isLeaf() {
        return root.left == null && root.right == null;
    }

    /**
     * Returns the roots node data value.
     *
     * @return data of root node.
     */
    public E getData() {
        return root.data;
    }

    public void setLeft(E data) {
        root.left = new Node<E>(data);
    }

    public void setRight(E data) {
        root.right = new Node<E>(data);
    }

    /**
     * Tree node represents data in a binary tree.
     *
     * @param <E> data type of the binary tree.
     */
    protected static class Node<E> implements Comparable<E> {
        protected E data;
        protected Node<E> left, right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public int compareTo(E o) {
            return 0;
        }
    }
}
