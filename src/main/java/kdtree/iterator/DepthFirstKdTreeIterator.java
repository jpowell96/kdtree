package kdtree.iterator;

import kdtree.KdTree;
import kdtree.Spatial;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstKdTreeIterator<T extends Spatial> extends KdTreeIterator<T> {
    Deque<KdTree.KdTreeNode<T>> stack;

    public DepthFirstKdTreeIterator(KdTree.KdTreeNode<T> root) {
        this.stack = new LinkedList<>();
        if (root != null) {
            stack.addLast(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        //1. Get the next kdNode
        KdTree.KdTreeNode<T> node = stack.removeLast();

        if (node.getRightChild() != null) {
            stack.addLast(node.getRightChild());
        }

        if (node.getLeftChild() != null) {
            stack.addLast(node.getLeftChild());
        }

        return node.getData();
    }
}
