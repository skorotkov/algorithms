package tree;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {
    @Test
    public void testAdd() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertTrue("search root elem", tree.search(10));
        Assert.assertTrue("search leaf elem", tree.search(7));
        Assert.assertTrue("search internal elem", tree.search(5));
        Assert.assertFalse("search absent elem", tree.search(3));
    }

    @Test
    public void testRemove() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(10).add(5).add(7).add(1);
        Assert.assertTrue("remove root elem", tree.remove(10));
        Assert.assertTrue("remove leaf elem", tree.remove(7));
        Assert.assertTrue("remove internal elem", tree.remove(5));
        Assert.assertFalse("remove absent elem", tree.remove(3));
    }

}
