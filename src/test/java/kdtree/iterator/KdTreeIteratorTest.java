package kdtree.iterator;

import kdtree.DimensionalKdTree;
import kdtree.KdTree;
import model.Star;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KdTreeIteratorTest {

    @Test
    public void dfsIteratorTest() {
        //Arrange - Create a KdTree create a DFSIterator for it
        KdTree<Star> kdTree = new DimensionalKdTree<>(smallTree());
        Iterator<Star> dfsIterator = kdTree.iterator(TreeIteratorType.DEPTH_FIRST);

        //Act: Go through the tree

    }

    @Test
    public void bfsIteratorTest() {
        //Arrange - Create a KdTre create a BFSIterator for it
        KdTree<Star> kdTree = new DimensionalKdTree<>(smallTree());
        Iterator<Star> bfsIterator = kdTree.iterator(TreeIteratorType.BREADTH_FIRST);


    }

    private List<Star> smallTree() {
        List<Star> data = new LinkedList<>();

        Star a = new Star("Sirius",1, new int[]{0,0,0});
        Star b = new Star("Arcturus", 2, new int[]{1,1,1});
        Star c = new Star("Betelgeuse",3,  new int[]{2,2,2});
        Star d = new Star("Aldebaran",4, new int[]{-1,-1,-1});
        Star e = new Star("Fomalhaut", 5, new int[]{4,4,4});


        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);
        data.add(e);
        return data;
    }
}
