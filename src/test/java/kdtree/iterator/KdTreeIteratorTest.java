package kdtree.iterator;

import kdtree.KdTree;
import kdtree.SpacePartitioningTree;
import model.Star;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class KdTreeIteratorTest {

    @Test
    public void dfsIteratorTest() {
        //Arrange - Create a SpacePartitioningTree create a DFSIterator for it
        SpacePartitioningTree<Star> kdTree = new KdTree<>(smallTree());
        Iterator<Star> dfsIterator = kdTree.iterator(TreeIteratorType.DEPTH_FIRST);

        //Act: Go through the tree

    }

    @Test
    public void bfsIteratorTest() {
        //Arrange - Create a KdTre create a BFSIterator for it
        SpacePartitioningTree<Star> kdTree = new KdTree<>(smallTree());
        Iterator<Star> bfsIterator = kdTree.iterator(TreeIteratorType.BREADTH_FIRST);


    }

    private List<Star> smallTree() {
        List<Star> data = new LinkedList<>();

        Star a = new Star("Sirius",1, new double[]{0,0,0});
        Star b = new Star("Arcturus", 2, new double[]{1,1,1});
        Star c = new Star("Betelgeuse",3,  new double[]{2,2,2});
        Star d = new Star("Aldebaran",4, new double[]{-1,-1,-1});
        Star e = new Star("Fomalhaut", 5, new double[]{4,4,4});


        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);
        data.add(e);
        return data;
    }
}
