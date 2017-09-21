package tree;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

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
    private TreeNode<E> minusInfinityNode;

    public BinarySearchTree() {
        minusInfinityNode = new TreeNode<>(null, null);
    }

    public boolean search(E element) {
        TreeNode<E> curr = minusInfinityNode.right;
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
        if (minusInfinityNode.right == null) {
            minusInfinityNode.right = new TreeNode<>(element, minusInfinityNode);
            return this;
        }
        TreeNode<E> curr = minusInfinityNode.right;
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
        TreeNode<E> curr = minusInfinityNode.right;
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
        if (node.left == null && node.right == null) {
            if (node.parent.left == node)
                node.parent.left = null;
            else
                node.parent.right = null;
            node.parent = null;
        } else if (node.left == null) {
            if (node.parent.left == node)
                node.parent.left = node.right;
            else
                node.parent.right = node.right;
            node.parent = null;
        } else if (node.right == null) {
            if (node.parent.left == node)
                node.parent.left = node.left;
            else
                node.parent.right = node.left;
            node.parent = null;
        } else {
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

    public <R> R inOrderTraversal(Function<TreeNode<E>, R> visitor, R zero, BinaryOperator<R> reducer, Function<R, Boolean> terminator) {
        if (minusInfinityNode.right == null)
            return zero;

        return reducer.apply(inOrderTraversal(minusInfinityNode.right, visitor, reducer, terminator), zero);
    }

    private <R> R inOrderTraversal(TreeNode<E> root, Function<TreeNode<E>, R> visitor, BinaryOperator<R> reducer, Function<R, Boolean> terminator) {
        R res = visitor.apply(root);

        if (res != null && terminator.apply(res))
            return res;

        if (root.left != null)
            res = res != null ?
                    reducer.apply(res, inOrderTraversal(root.left, visitor, reducer, terminator)):
                    inOrderTraversal(root.left, visitor, reducer, terminator);

        if (res != null && terminator.apply(res))
            return res;

        if (root.right != null)
            res = res != null ?
                    reducer.apply(res, inOrderTraversal(root.right, visitor, reducer, terminator)):
                    inOrderTraversal(root.right, visitor, reducer, terminator);

        return res;
    }

    public <R> R preOrderTraversal(Function<TreeNode<E>, R> visitor, R zero, BinaryOperator<R> reducer, Function<R, Boolean> terminator) {
        if (minusInfinityNode.right == null)
            return zero;

        return preOrderTraversal(minusInfinityNode.right, visitor, reducer, terminator);
    }

    private <R> R preOrderTraversal(TreeNode<E> root, Function<TreeNode<E>, R> visitor, BinaryOperator<R> reducer, Function<R, Boolean> terminator) {
        R res = null;

        if (root.left != null)
            res = preOrderTraversal(root.left, visitor, reducer, terminator);
        if (res != null && terminator.apply(res))
            return res;

        res = res != null ?
                reducer.apply(res, visitor.apply(root)) :
                visitor.apply(root);
        if (res != null && terminator.apply(res))
            return res;

        if (root.right != null)
            res = res != null ?
                    reducer.apply(res, preOrderTraversal(root.right, visitor, reducer, terminator)) :
                    preOrderTraversal(root.right, visitor, reducer, terminator);

        return res;
    }

    public boolean all(Predicate<E> predicate) {
        return preOrderTraversal(n -> predicate.test(n.value),true, (a, b) -> a && b, b -> !b);
    }

    public boolean any(Predicate<E> predicate) {
        return preOrderTraversal(n -> predicate.test(n.value),false, (a, b) -> a || b, b -> b);
    }

}
