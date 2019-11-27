package api.services;

import api.responses.LocationResponse;
import model.Star;

import java.util.List;
import java.util.Set;

interface AbstractKdTreeService {


     LocationResponse nearestNeighbor(Star location);
     List<LocationResponse> kNearestNeighbors(Star location, int k);
     Set<LocationResponse> radiusSearch(Star location, double radius);
}
