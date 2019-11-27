package csvreader;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader  {
    private File file;
    private String fileName;

    public CSVReader(String fileName) throws FileNotFoundException {
        this.file = new File(fileName);
        this.fileName = fileName;
    }

    List<String[]> rows() {
       try (BufferedReader r = new BufferedReader(new FileReader(this.file))) {
           List<String[]> lines = r.lines()
                   .map(line -> line.split(","))
                   .collect(Collectors.toList());
           return lines;
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        return Collections.emptyList();
    }

    public static List<String[]> rows(String file, String separator) {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            List<String[]> lines = r.lines()
                    .map(line -> line.split(separator))
                    .collect(Collectors.toList());
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }


}
