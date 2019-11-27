package csvreader;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Using CSV Reader to print out stuff");
        System.out.println(System.getProperty("user.dir"));
        for (String[] row : CSVReader.rows("./src/main/resources/smallStars.csv", ",")) {
            System.out.println(Arrays.toString(row));
        }
    }
}
