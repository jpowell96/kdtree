package kdtree;

import kdtree.iterator.TreeIteratorType;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface KdTree<T extends Spatial> extends Iterable<T> {
    int size();


    T nearestNeighbor(T location);
    List<T> kNearestNeighbors(T location, int k);
    Set<T> radiusSearch(T location, double radius);
    Iterator<T> iterator(TreeIteratorType type);


     interface KdTreeNode<T> {
        T getData();
        boolean isLeaf();
        KdTreeNode<T> getLeftChild();
        KdTreeNode<T> getRightChild();
    }
}
