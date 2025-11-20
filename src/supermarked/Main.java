package supermarked;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {

        //  Load products from CSV
        CSVReader reader = new CSVReader();
        Map<String, Items> allProducts = reader.loadProducts("data-resources/varer.csv", false);
        Map<String, Items> saleProducts = reader.loadProducts("data-resources/tilbud.csv", true);

        System.out.println("All products loaded: " + allProducts.size());
        System.out.println("Sale products loaded: " + saleProducts.size());

        //  Manual basket
        List<Items> manualBasket = new ArrayList<>();
        manualBasket.add(new Items("111", "Banana", 2.5, 2, "pcs", false));
        manualBasket.add(new Items("222", "Milk", 10.0, 1, "liter", true));
        manualBasket.add(new Items("111", "Banana", 2.5, 1, "pcs", false)); // duplicate

        System.out.println("\n=== Manual Basket Checkout ===");
        CashRegister manualRegister = new CashRegister();
        manualRegister.checkout(manualBasket, allProducts, saleProducts);

        //  Robot basket
        BasketRobot robot = new BasketRobot(allProducts, saleProducts);
        List<Items> robotBasket = robot.fillBasket(10); // pick 10 random items

        System.out.println("\n=== Robot Basket Checkout ===");
        CashRegister robotRegister = new CashRegister();
        robotRegister.checkout(robotBasket, allProducts, saleProducts);
    }
    }

