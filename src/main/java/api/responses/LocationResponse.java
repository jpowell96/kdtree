package api.responses;

import model.Star;

public class LocationResponse {

    private Star star;
    private double distance;

    public LocationResponse(Star star, double distance) {
        this.star = star;
        this.distance = distance;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
