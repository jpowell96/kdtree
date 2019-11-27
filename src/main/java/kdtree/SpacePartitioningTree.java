package kdtree;

import kdtree.boundingbox.BoundingBox;
import kdtree.iterator.TreeIteratorType;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface SpacePartitioningTree<T extends Spatial> extends Iterable<T> {
    int size();
    T nearestNeighbor(T location);
    boolean contains(T location);
    List<T> kNearestNeighbors(T location, int k);
    Set<T> radiusSearch(T location, double radius);
    Set<T> rangeSearch(T location, BoundingBox<T> myBoundingBox);
    Iterator<T> iterator(TreeIteratorType type);


     interface KdTreeNode<T> {
        T getData();
        boolean isLeaf();
        KdTreeNode<T> getLeftChild();
        KdTreeNode<T> getRightChild();
    }
}
