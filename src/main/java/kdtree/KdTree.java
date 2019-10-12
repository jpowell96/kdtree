package kdtree;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface KdTree<T extends Spatial> {
    int size();


    T nearestNeighbor(T location);
    List<T> kNearestNeighbors(T location, int k);
    Set<T> radiusSearch(T location, double radius);


     interface KdTreeNode<T> {
        T getData();
        boolean isLeaf();
        KdTreeNode<T> getLeftChild();
        KdTreeNode<T> getRightChild();
    }
}
