package model;

import kdtree.Spatial;

public class Coordinate implements Spatial<Coordinate> {
    double x;
    double y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int maxDimensions() {
        return 2;
    }

    @Override
    public double getDim(int dim) {
        if (dim % 2 == 0) {
            return this.x;
        } else {
            return this.y;
        }
    }

    @Override
    public double distance(Coordinate other) {
        return Math.sqrt(squaredDistance(other));
    }

    @Override
    public double distance(Coordinate other, int dim) {
        return Math.sqrt(squaredDistance(other,dim));
    }

    @Override
    public double squaredDistance(Coordinate other) {
        return squaredDistance(other, 0) + squaredDistance(other, 1);
    }

    @Override
    public double squaredDistance(Coordinate other, int dim) {
        return Math.abs(Math.pow(other.getDim(dim), 2) - Math.pow(this.getDim(dim),2));
    }
}
