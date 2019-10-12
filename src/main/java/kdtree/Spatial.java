package kdtree;

/**
 * An interface to represent kdtree.Spatial data
 *
 * */
public interface Spatial<T> {
    int maxDimensions();
    double getDim(int dim);
    double distance(T other);
    double distance(T  other, int dim);
    double squaredDistance(T other);
    double squaredDistance(T other, int dim);
}
