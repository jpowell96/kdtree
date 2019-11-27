package api.requests;

import model.Star;

public class KNNRequest {
    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    private Star star;
    private int k;

    KNNRequest(Star star, int k ) {
        this.k = k;
        this.star = star;
    }
}
