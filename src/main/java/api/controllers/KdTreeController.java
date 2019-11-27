package api.controllers;

import api.requests.KNNRequest;
import api.requests.RadiusRequest;
import api.responses.LocationResponse;
import api.services.KdTreeService;
import model.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class KdTreeController {

    @Autowired
    private KdTreeService kdTreeService;

    @GetMapping(value="/nearestNeighbor", produces = MediaType.APPLICATION_JSON_VALUE)
    public LocationResponse nearestNeighbor() {
       return kdTreeService.nearestNeighbor(new Star(new double[]{40,40,40}));
    }

    @GetMapping("/kNearestNeighbors")
    public List<LocationResponse> nearestNeighbor(@RequestBody KNNRequest request) {
        return kdTreeService.kNearestNeighbors(request.getStar(), request.getK());
    }

    @GetMapping("/radius")
    public Set<LocationResponse> radiusSearch(@RequestBody RadiusRequest request) {
        return kdTreeService.radiusSearch(request.getStar(), request.getRadius());
    }

    @GetMapping(value="/nearestNeighbor/{id}")
    public Star nearestNeighbor(@RequestParam int id) {
        return null;
    }

}
