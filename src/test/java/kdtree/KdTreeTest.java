package kdtree;


import csvreader.CSVReader;
import model.Coordinate;
import model.Star;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class KdTreeTest {
    private final int X_AXIS_IDX = 17;
    private final int Y_VALUE_IDX = 18;
    private final int Z_VALUE_IDX = 19;
    private final int ID_IDX = 0;

    @Test
    public void givenEmptyListKdTreeRootIsNull() {
        KdTree<Star> kdtree = new KdTree<>(Collections.emptyList());
        Assertions.assertEquals(null, kdtree.getRoot());
    }

    @Test
    public void populateTreeFromFile() {
        List<String[]> rows = CSVReader.rows("./src/main/resources/smallStars.csv", ",");
        List<Star> stars = rows.subList(1, 10)
                .stream()
                .map(starData -> toStar(starData))
                .collect(Collectors.toList());
        KdTree<Star> kdTree = new KdTree<>(stars);
        System.out.println(kdTree.nearestNeighbor(new Star(new double[]{0,0,0})));
        Assertions.assertTrue(kdTree.size() > 0);
    }

    @Test
    public void itWorksOnDIfferentDataTypes() {
        KdTree<Coordinate> kdTree = new KdTree<>();
    }

    @Test
    public void givenListOfDataItBuildsTree() {
        KdTree<Star> kdTree = new KdTree<>(bigTree());
        Assertions.assertEquals(9, kdTree.size());
    }

    public void itReturnsLocationIfNearestNeighborInTree() {
        //Arrange: Setup the tree
        KdTree<Star> kdTree = new KdTree<>(bigTree());

        //Act: Find the nearest neighbhor to a point that is in the tree: {5,5,5}
        Star nearestNeighbhor = kdTree.nearestNeighbor(new Star(new double[]{5,5,5}));

        //Assert that it finds the star that is in the tree already
        Assertions.assertEquals(nearestNeighbhor, bigTree().get(5));
    }

    @Test
    public void itFindsNearestNeighbhorOnSmallTree() {
        //Arrange: Create a kdTree
        KdTree<Star> kdTree = new KdTree<>(smallTree());

        //Act: Call nearest neighbhor to point {5,5,5}
        Star nearestNeighbhor =  kdTree.nearestNeighbor(new Star(new double[]{5,5,5}));

        //Assert: Expect answer to be {2, 2, 2}
        Assertions.assertArrayEquals(new double[]{2,2,2}, nearestNeighbhor.getCoordinates());
    }

    @Test
    public void itFindsTheNearestNeighbhorOnBigTree() {
        //Arrange: Create a kdTree
        KdTree<Star> kdTree = new KdTree<>(bigTree());

        //Act: Call nearest neighbhor to point {10,10,10}
        Star nearestNeighbhor =  kdTree.nearestNeighbor(new Star(new double[]{10,10,10}));

        //Assert: Expect answer to be {9, 9, 9}
        Assertions.assertArrayEquals(new double[]{9,9,9}, nearestNeighbhor.getCoordinates());

    }


    @Test
    public void itReturnsNullIfLocationIsNull() {
        KdTree<Star> kdTree = new KdTree<>();

        //Act: Call nearest neighbor on null
        Star nearestNeighbor = kdTree.nearestNeighbor(null);

        //Assert
        Assertions.assertNull(nearestNeighbor);
    }

    @Test
    public void itFindsNearestNeighborGivenNegativeLocation() {
        //Arrange: Create a kdTree
        KdTree<Star> kdTree = new KdTree<>(bigTree());

        //Act: Call nearest neighbor to point {-100,-100,-100}
        Star nearestNeighbhor =  kdTree.nearestNeighbor(new Star(new double[]{-100,-100,-100}));

        //Assert: Expect answer to be {0, 0, 0}
        Assertions.assertArrayEquals(new double[]{0,0,0}, nearestNeighbhor.getCoordinates());

    }

    @Test
    public void kNearestNeighborsFindsSameValueAsNN() {
        KdTree<Star> kdTree = new KdTree<>(bigTree());

        //Act: Call nearest neighbhor to point {-100,-100,-100}
        Star nearestNeighbhor =  kdTree.nearestNeighbor(new Star(new double[]{-100,-100,-100}));
        List<Star> kNN = kdTree.kNearestNeighbors(new Star(new double[]{-100,-100,-100}), 1);

        Assertions.assertEquals(1, kNN.size());
        Assertions.assertEquals(nearestNeighbhor, kNN.get(0));
    }

    @Test
    public void kNearestNeighborsFindsNearestNeighbors() {
        KdTree<Star> kdTree = new KdTree<>(bigTree());

        //Act: Call nearest neighbhor to point {-100,-100,-100}
        List<Star> kNN = kdTree.kNearestNeighbors(new Star(new double[]{-100,-100,-100}), 3);

        //Expected: [{0,0,0} , {1,1,1}, {2,2,2}]
        List<Star> expected = bigTree().subList(0,3);

        Assertions.assertEquals(expected, kNN);
    }

    @Test
    public void radiusSearchFindsAllDataInRange() {
        List<Star> bigTree = bigTree();
        KdTree<Star> kdTree = new KdTree<>(bigTree);

        //Act: Call nearest neighbhor to point {-100,-100,-100}
        Set<Star> inRange = kdTree.radiusSearch(new Star(new double[]{0, 0,0}), 5);

        //Assert:
        Assertions.assertTrue(inRange.containsAll(bigTree.subList(0,3)));

    }


    private List<Star> smallTree() {
        List<Star> data = new LinkedList<>();

        Star a = new Star("Sirius",1, new double[]{0,0,0});
        Star b = new Star("Arcturus", 2, new double[]{1,1,1});
        Star c = new Star("Betelgeuse",3,  new double[]{2,2,2});

        data.add(a);
        data.add(b);
        data.add(c);

        return data;
    }

    private Star toStar(String[] starData) {
        //x = 17, y=18, z=19
        Star star = new Star();
        double[] coordinates = {Double.parseDouble(starData[X_AXIS_IDX]),
                Double.parseDouble(starData[Y_VALUE_IDX]),
                Double.parseDouble(starData[Z_VALUE_IDX])};
        star.setCoordinates(coordinates);
        star.setId(Integer.parseInt(starData[ID_IDX]));
        return star;
    }

    private List<Star> bigTree() {
        List<Star> data = new LinkedList<>();
        Star a = new Star("Sirius", 1, new double[]{0,0,0});
        Star b = new Star("Arcturus", 2, new double[]{1,1,1});
        Star c = new Star("Rigel", 3, new double[]{2,2,2});
        Star d = new Star("Aldebaran",4, new double[]{3,3,3});
        Star e = new Star("Fomalhaut", 5, new double[]{4,4,4});
        Star f = new Star("Vega", 5, new double[]{5,5,5});
        Star g = new Star("Deneb", 6, new double[]{6,6,7});
        Star h = new Star("Canopus", 7, new double[]{7,7,7});
        Star i = new Star("Altair" ,8, new double[]{8,8,8});
        Star j = new Star("Algol", 9, new double[]{9,9,9});
        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);
        data.add(e);
        data.add(f);
        data.add(g);
        data.add(h);
        data.add(i);
        data.add(j);
        return data;
    }
}
