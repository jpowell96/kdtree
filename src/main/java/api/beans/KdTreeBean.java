package api.beans;

import csvreader.CSVReader;
import kdtree.KdTree;
import kdtree.SpacePartitioningTree;
import model.Star;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class KdTreeBean {
    private final int X_AXIS_IDX = 17;
    private final int Y_VALUE_IDX = 18;
    private final int Z_VALUE_IDX = 19;
    private final int ID_IDX = 0;

    @Bean(name="smallKdTree")
    public SpacePartitioningTree<Star> SmallKdTree() {
        List<String[]> rows = CSVReader.rows("./src/main/resources/smallStars.csv", ",");
        List<Star> stars = rows.subList(1, rows.size())
                .stream()
                .map(starData -> toStar(starData))
                .collect(Collectors.toList());

        return new KdTree<>(stars);

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
}
