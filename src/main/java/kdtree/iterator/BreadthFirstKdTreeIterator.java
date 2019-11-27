package kdtree.iterator;

import kdtree.SpacePartitioningTree;
import kdtree.Spatial;

import java.util.Deque;
import java.util.LinkedList;

public class BreadthFirstKdTreeIterator<T extends Spatial> extends KdTreeIterator<T> {

    private Deque<SpacePartitioningTree.KdTreeNode<T>> queue;

    public BreadthFirstKdTreeIterator(SpacePartitioningTree.KdTreeNode<T> root) {
        this.queue = new LinkedList<>();
        if (root != null) {
            queue.addLast(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        //Remove the kdNode that's on the queue
        SpacePartitioningTree.KdTreeNode<T> node = queue.removeFirst();

        if (node.getLeftChild() != null) {
            queue.addLast(node.getLeftChild());
        }

        if (node.getRightChild() != null) {
            queue.addLast(node.getRightChild());
        }

        return node.getData();
    }
}
