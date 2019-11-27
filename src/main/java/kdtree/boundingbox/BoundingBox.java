package kdtree.boundingbox;

import kdtree.Spatial;

public interface BoundingBox<T extends Spatial> {
    boolean intersectsBoundingBox(T location);
    boolean isInBounds(T location);
}
