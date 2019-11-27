package api.requests;

import model.Star;

public class RadiusRequest {
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    private double radius;
    private Star star;

    public RadiusRequest(Star star, double radius) {
        this.radius = radius;
        this.star = star;
    }
}
