package model;

import kdtree.Spatial;

import java.util.Arrays;
import java.util.Objects;

public class Star implements Spatial<Star> {
    int id;
    String name;
    private int[] coordinates;


    public Star(String name, int id, int[] coordinates) {
        this.name = name;
        this.id = id;
        this.coordinates = coordinates;
    }

    public Star(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinates);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Star)) {
            return false;
        }

        return Arrays.equals(this.coordinates, ((Star) obj).getCoordinates());
    }

    @Override
    public String toString() {
        return String.format("Star \n id: %d \n Coordinates: ", this.id) + Arrays.toString(this.coordinates);

    }

    @Override
    public int maxDimensions() {
        return 3;
    }

    /** Get the value at the given dimension*/
    public double getDim(int dim) {
        int modDim = dim % 3;
        return coordinates[modDim];
    }

    /** Find the euclidean distance to an other model.Star*/
    public double distance(Star other) {

        return Math.sqrt(this.squaredDistance(other));
    }

    /** Find the distance to another star on a given dimention*/
    public double distance(Star other, int dim) {
        return Math.sqrt(this.squaredDistance(other, dim));
    }

    @Override
    public double squaredDistance(Star other) {
        return this.squaredDistance(other,0)
                + this.squaredDistance(other, 1)
                + this.squaredDistance(other, 2);

    }

    @Override
    public double squaredDistance(Star other, int dim) {
        return Math.pow(Math.abs(this.getDim(dim) - other.getDim(dim)), 2);
    }
}
