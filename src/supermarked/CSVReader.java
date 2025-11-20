package supermarked;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    public Map<String, Items> loadProducts(String filename, boolean isSale) {
        Map<String, Items> itemsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // Skip header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {

                // Split line by ;
                String[] fields = line.split(";");

                // Safety check
                if (fields.length < 7) continue;

                // Map relevant fields to Items
                String id = fields[0];
                String name = fields[1];
                double price = Double.parseDouble(fields[3]);
                double quantity = Double.parseDouble(fields[5]);
                String unit = fields[6];

                // Create Items object
                Items item = new Items(id, name, price, quantity, unit, isSale);

                // Add to map
                itemsMap.put(id, item);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in file: " + filename);
            e.printStackTrace();
        }

        return itemsMap;
    }
}
