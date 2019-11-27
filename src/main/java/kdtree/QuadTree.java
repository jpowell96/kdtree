package kdtree;


import kdtree.boundingbox.BoundingBox;
import kdtree.iterator.TreeIteratorType;
import model.Coordinate;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;


public class QuadTree<T extends Spatial> implements SpacePartitioningTree<T> {
    T data;
    QuadTree<T> northEast;
    QuadTree<T> northWest;
    QuadTree<T> southEast;
    QuadTree<T> southWest;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T nearestNeighbor(T location) {
        return null;
    }

    @Override
    public boolean contains(T location) {
        return false;
    }

    @Override
    public List<T> kNearestNeighbors(T location, int k) {
        return null;
    }

    @Override
    public Set<T> radiusSearch(T location, double radius) {
        return null;
    }

    @Override
    public Set<T> rangeSearch(T location, BoundingBox<T> myBoundingBox) {
        return null;
    }

    @Override
    public Iterator<T> iterator(TreeIteratorType type) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }
}
