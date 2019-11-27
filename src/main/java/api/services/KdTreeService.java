package api.services;

import api.responses.LocationResponse;
import kdtree.SpacePartitioningTree;
import model.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KdTreeService implements AbstractKdTreeService {

    @Autowired
    @Qualifier("smallKdTree")
    private SpacePartitioningTree<Star> spacePartitioningTree;

    public SpacePartitioningTree<Star> getSpacePartitioningTree() {
        return spacePartitioningTree;
    }

    public void setSpacePartitioningTree(SpacePartitioningTree<Star> spacePartitioningTree) {
        this.spacePartitioningTree = spacePartitioningTree;
    }

    public LocationResponse nearestNeighbor(Star location) {
        Star nearestNeighbor = spacePartitioningTree.nearestNeighbor(location);
        return new LocationResponse(nearestNeighbor, nearestNeighbor.distance(location));
    }

    public List<LocationResponse> kNearestNeighbors(Star location, int k) {
        return spacePartitioningTree.kNearestNeighbors(location, k)
                .stream()
                .map(star -> new LocationResponse(star, location.distance(star)))
                .collect(Collectors.toList());
    }

    public Set<LocationResponse> radiusSearch(Star location, double radius) {
        return spacePartitioningTree.radiusSearch(location, radius)
                .stream()
                .map(star -> new LocationResponse(star, location.distance(star)))
                .collect(Collectors.toSet());
    }


}
