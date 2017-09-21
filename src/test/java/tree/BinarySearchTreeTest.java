package tree;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {
    @Test
    public void testAdd() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(8).add(1).add(5);
        Assert.assertTrue(isOrdered(tree));
    }

    @Test
    public void testSearch() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Assert.assertFalse("search in empty tree", tree.search(3));

        tree.add(10).add(5).add(7).add(8).add(1).add(5);
        Assert.assertTrue("search root elem", tree.search(10));
        Assert.assertTrue("search leaf elem", tree.search(7));
        Assert.assertTrue("search internal elem", tree.search(5));
        Assert.assertFalse("search absent elem", tree.search(3));
    }

    @Test
    public void testRemove() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertTrue("remove leaf elem", tree.remove(7));
        Assert.assertFalse("search removed leaf elem", tree.search(7));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertTrue("remove internal elem", tree.remove(5));
        Assert.assertFalse("search removed internal elem", tree.search(5));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1).add(6);
        Assert.assertTrue("remove internal elem with big tree on the right", tree.remove(5));
        Assert.assertFalse("search removed internal elem with big tree on the right", tree.search(5));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertFalse("remove absent elem", tree.remove(3));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1).add(2);
        Assert.assertTrue("remove internal elem with right child", tree.remove(1));
        Assert.assertFalse("search removed internal elem with right child", tree.search(1));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(11).add(1);
        Assert.assertTrue("remove internal elem with right child", tree.remove(5));
        Assert.assertFalse("search removed internal elem with right child", tree.search(5));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(11).add(12).add(1).add(2);
        Assert.assertTrue("remove internal elem with left child", tree.remove(11));
        Assert.assertFalse("search removed internal elem with left child", tree.search(11));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertTrue("remove root elem with one child", tree.remove(10));
        Assert.assertFalse("search removed root elem with one child", tree.search(10));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10).add(15).add(5).add(7).add(1);
        Assert.assertTrue("remove root elem with two children", tree.remove(10));
        Assert.assertFalse("search removed root elem with two children", tree.search(10));
        Assert.assertTrue(isOrdered(tree));

        tree = new BinarySearchTree<>();
        tree.add(10);
        Assert.assertTrue("remove root elem with no children", tree.remove(10));
        Assert.assertFalse("search removed root elem with no children", tree.search(10));

        tree = new BinarySearchTree<>();
        Assert.assertFalse("remove from empty tree", tree.remove(10));
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Assert.assertTrue("in-order traversal on empty tree", isOrdered(tree));

        tree.add(10).add(5).add(7).add(1).add(15).add(45);
        Assert.assertTrue(isOrdered(tree));
    }

    @Test
    public void testPreOrderTraversal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1).add(15).add(45);
        Assert.assertTrue(isOrdered(tree));
        Assert.assertEquals("1, 5, 7, 10, 15, 45",
                tree.preOrderTraversal(n -> n.value.toString(), "", (a, b) -> a + ", " + b, b -> false));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(-1).add(15).add(45);
        Assert.assertFalse("all > 0",
                tree.preOrderTraversal(n -> n.value > 0, true, (a, b) -> a && b, b -> false));

        tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(-1).add(15).add(45);
        Assert.assertEquals("sum of all", (Integer)81,
                tree.preOrderTraversal(n -> n.value, 0, (a, b) -> a + b, b -> false));
    }

    @Test
    public void testAllAny() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(10).add(5).add(7).add(1).add(15).add(45);
        Assert.assertTrue(tree.all(v -> v > 0));
        Assert.assertTrue(tree.any(v -> v > 15));
        Assert.assertFalse(tree.all(v -> v > 15));
        Assert.assertFalse(tree.any(v -> v < 0));
    }

    private <E extends Comparable<E>> boolean isOrdered(BinarySearchTree<E> tree) {
        return tree.inOrderTraversal(n -> n.left == null  || n.value.compareTo(n.left.value) > 0 &&
                                          n.right == null || n.value.compareTo(n.right.value) < 0,
                                     true,
                                     (a, b) -> a && b,
                                     b -> !b);
    }
}
