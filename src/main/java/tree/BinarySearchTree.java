package tree;

public class BinarySearchTree<E extends Comparable<E>> {
    class TreeNode<V extends Comparable<V>> {
        V value;
        TreeNode<V> parent;
        TreeNode<V> left;
        TreeNode<V> right;

        TreeNode(V value, TreeNode<V> parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }
    private TreeNode<E> root;

    public BinarySearchTree() {
    }


    public boolean search(E element) {
        TreeNode<E> curr = root;
        while (curr != null) {
            int compareResult = curr.value.compareTo(element);
            if (compareResult == 0)
                return true;
            else if (compareResult < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }

    public BinarySearchTree<E> add(E element) {
        if (root == null) {
            root = new TreeNode<>(element, null);
            return this;
        }
        TreeNode<E> curr = root;
        while (true) {
            int compareResult = curr.value.compareTo(element);
            if (compareResult == 0) {
                return this;
            } else if (compareResult < 0) {
                if (curr.right == null) {
                    curr.right = new TreeNode<>(element, curr);
                    return this;
                } else {
                    curr = curr.right;
                }
            } else {
                if (curr.left == null) {
                    curr.left = new TreeNode<>(element, curr);
                    return this;
                } else {
                    curr = curr.left;
                }
            }
        }
    }

    public boolean remove(E element) {
        TreeNode<E> curr = root;
        while (curr != null) {
            int compareResult = curr.value.compareTo(element);
            if (compareResult == 0) {
                remove(curr);
                return true;
            } else if (compareResult < 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return false;
    }

    private void remove(TreeNode<E> node) {
        if (node.left == null && node.right == null)
            node.parent = null;
        else if (node.left == null)
            node.parent.right = node.right;
        else if (node.right == null)
            node.parent.left = node.left;
        else {
            TreeNode<E> toRemove = findMin(node.right);
            node.value = toRemove.value;
            remove(toRemove);
        }
    }

    private TreeNode<E> findMin(TreeNode<E> node) {
        while (true) {
            if (node.left == null)
                return node;
            else
                node = node.left;
        }
    }
}
