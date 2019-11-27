package kdtree;

import kdtree.boundingbox.BoundingBox;
import kdtree.iterator.BreadthFirstKdTreeIterator;
import kdtree.iterator.DepthFirstKdTreeIterator;
import kdtree.iterator.TreeIteratorType;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class KdTree<T extends Spatial>  implements SpacePartitioningTree<T>
 {
    private KdNode<T> root;
    private int size = 0;

    public KdTree() {
        this.root = null;
    }

    public KdTree(Collection<? extends T> data) {
        this.root = buildTree(new ArrayList<>(data), 0);
    }


    private KdNode<T> buildTree(List<? extends T> data, int dim) {
        if (data.isEmpty()) {
            return null;
        }
        List<T> dataList = new LinkedList<>(data);
        T median = dataList.get(dataList.size() / 2);
        KdNode<T> node = new KdNode<>(median, dim % median.maxDimensions());

        List<T> leftChildren =  dataList.stream()
                .filter(item -> item.getDim(dim) < median.getDim(dim))
                .collect(Collectors.toList());

        List<T> rightChildren =  dataList.stream()
                .filter(item -> item.getDim(dim) > median.getDim(dim))
                .collect(Collectors.toList());

        node.setLeftChild(buildTree(leftChildren, dim + 1));
        node.setRightChild(buildTree(rightChildren, dim + 1));

        this.size++;
        return node;
    }

    public KdNode<T> getRoot() {
        return this.root;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T nearestNeighbor(T location) {
        if (location == null) {
            return null;
        }
        return nearestNeighborHelper(location, root, null);
    }

     @Override

     public boolean contains(T location) {
        Iterator<T> iterator = this.iterator();

        while (iterator.hasNext()) {
            if (location.equals(iterator.next())) {
                return true;
            }
        }

        return false;
     }

     private T nearestNeighborHelper(T location, KdNode<T> root, T currentBest) {
        if (root == null || location == null) {
            return null;
        }

        //1. At the current node, check if it's closer than the currentBest so far
        double currentDistance = location.distance(root.getData());
        double bestDistance = currentBest == null ? Double.MAX_VALUE : location.distance(currentBest);
        if (currentBest == null || currentDistance < location.distance(currentBest)) {
           currentBest = root.getData();
        }

        //2. Decide whether to keep traversing the tree to the left and to the right
        if (continueSearch(location, root.getLeftChild(), bestDistance)) {
            T leftBest = nearestNeighborHelper(location, root.getLeftChild(), currentBest);

            if (leftBest != null && location.distance(leftBest) < location.distance(currentBest)) {
                currentBest = leftBest;
            }
        }

        if (continueSearch(location, root.getRightChild(), bestDistance)) {
            T rightBest = nearestNeighborHelper(location, root.getRightChild(), currentBest);

            if (rightBest != null && location.distance(rightBest) < location.distance(currentBest)) {
                currentBest = rightBest;
            }
        }

        return currentBest;
    }



    private boolean continueSearch(T location, KdNode<T> child, double bestDistance) {
        if (child == null) {
            return false;
        }

        return (Math.abs(location.distance(child.getData(), child.getSplittingDimension()))) < bestDistance;
    }



    @Override
    public List<T> kNearestNeighbors(T location, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("kNNeighbors expects a non-negative integar. Given : " + k);
        }

        //Priority Queue will keep the furthest at the top of the queue
        PriorityQueue<T> pq = new PriorityQueue<T>(k, Comparator.comparingDouble( node -> -node.distance(location)));

        //Recursively populate nearest neighbors
        pq = kNearestNeighborsHelper(location,root, k, pq);

        //Pop the first k elements into a list.
        LinkedList<T> kNearest = new LinkedList<>();

        while (!pq.isEmpty()) {
            kNearest.addFirst(pq.poll());
        }
        return kNearest;
    }

    private PriorityQueue<T> kNearestNeighborsHelper(T location,KdNode<T> root, int k, PriorityQueue<T> pq) {
        if (root == null || location == null) {
            return pq;
        }

        //The furthest node from the location that's still in the pq
        T maxBest = pq.peek();
        double maxBestDistance = maxBest == null ? Double.MAX_VALUE : location.distance(maxBest);
        double rootDistance = location.distance(root.getData());

        if (pq.size() < k) {
            pq.add(root.getData());

            //Go down the tree that has the closest children
            kNearestNeighborsHelper(location, root.getLeftChild(), k, pq);
            kNearestNeighborsHelper(location, root.getRightChild(), k, pq);
        } else if (pq.size() == k) {
            //Remove maxBest from the top of the queue
            if (rootDistance < maxBestDistance) {
                //Remove the current best
                pq.poll();

                //Insert root into the pq
                pq.add(root.getData());
                maxBestDistance = rootDistance;
            }

            if (continueSearch(location, root.getLeftChild(), maxBestDistance)) {
                kNearestNeighborsHelper(location, root.getLeftChild(), k, pq);
            }

            if (continueSearch(location, root.getRightChild(), maxBestDistance)) {
                kNearestNeighborsHelper(location, root.getRightChild(), k, pq);
            }
        }

        return pq;
    }

    @Override
    public Set<T> radiusSearch(T location, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("kNNeighbors expects a non-negative integar. Given : " + radius);
        }
        return radiusSearchHelper(location, root, radius, new HashSet<>());
    }

     @Override
     public Set<T> rangeSearch(T location, BoundingBox<T> myBoundingBox) {
         return null;
     }


     private Set<T> radiusSearchHelper(T location, KdNode<T> root, double radius, Set<T> result) {
        if (root == null || location == null) {
            return result;
        }

        if (location.distance(root.getData()) <= radius) {
            result.add(root.getData());
        }


        if (continueSearch(location, root.getLeftChild(), radius)) {
           radiusSearchHelper(location, root.getLeftChild(),radius, result);
        }

        if (continueSearch(location, root.getRightChild(), radius)) {
           radiusSearchHelper(location, root.getRightChild(), radius, result);
        }

        return result;
    }

    //Finds the median of data in O(n) time
    private T quickSelect(List<T> listOfData, int k) {
        Random r = new Random();

        //Chooses a random number between [0, listOfData.size)
        int pivot =r.nextInt(listOfData.size());


        return null;
    }

     @Override
     public Iterator<T> iterator() {
         return  (new BreadthFirstKdTreeIterator<>(this.root));
     }

     public Iterator<T> iterator(TreeIteratorType type) {
        if (type == TreeIteratorType.BREADTH_FIRST) {
            return new BreadthFirstKdTreeIterator<>(this.root);
        } else if (type == TreeIteratorType.DEPTH_FIRST) {
            return new DepthFirstKdTreeIterator<>(this.root);
        }

        throw new IllegalArgumentException("TreeIteratorType : " + type + "not found.");
     }


     @Override
     public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = this.iterator();

        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
     }

     private static class KdNode<T> implements KdTreeNode {


        private T data;
        private int splittingDimension;
        private KdNode<T> leftChild;
        private KdNode<T> rightChild;

        public KdNode(T data, int dim) {
            this.data = data;
            this.splittingDimension = dim;
        }

        public int getSplittingDimension() {
            return this.splittingDimension;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public KdNode<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(KdNode<T> leftChild) {
            this.leftChild = leftChild;
        }

        public KdNode<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(KdNode<T> rightChild) {
            this.rightChild = rightChild;
        }

        public boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }
    }
}
