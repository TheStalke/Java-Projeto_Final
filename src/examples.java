import java.io.*;
import java.util.*;

public class examples {
    public static void main(String[] args) {
        String filePath = "D:\\IntelijProjects\\projetoFinalJavaAvancado\\src\\cars.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // read first line
            if (line != null) {
                // Split by semicolon
                String[] carsArray = line.split(";");

                // Convert to a List
                List<String> cars = Arrays.asList(carsArray);

                // Print them
                for (String car : cars) {
                    System.out.println(car.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
