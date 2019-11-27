package kdtree.boundingbox;

import kdtree.Spatial;
import kdtree.boundingbox.BoundingBox;

public class MyBoundingBox<T extends Spatial> implements BoundingBox<T> {
    T upperBound;
    T lowerBound;

    public MyBoundingBox(T upperBound, T lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean intersectsBoundingBox(T location) {
        return false;
    }

    @Override
    public boolean isInBounds(T location) {
        return false;
    }
}
