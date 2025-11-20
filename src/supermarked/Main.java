package supermarked;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) {

        // Load products from CSV
        CSVReader reader = new CSVReader();
        Map<String, Items> allProducts = reader.loadProducts("data-resources/varer.csv", false);
        Map<String, Items> saleProducts = reader.loadProducts("data-resources/tilbud.csv", true);

        //  Create a manual basket
        List<Items> basket = new ArrayList<>();
        basket.add(new Items("111", "Banana", 2.5, 2, "pcs", false));
        basket.add(new Items("222", "Milk", 10.0, 1, "liter", true));
        basket.add(new Items("111", "Banana", 2.5, 1, "pcs", false)); // duplicate to test grouping

        // Checkout manual basket
        CashRegister register = new CashRegister();
        System.out.println("=== Manual Basket ===");
        register.checkout(basket, allProducts, saleProducts);


        // Generate a random basket with BasketRobot
        BasketRobot robot = new BasketRobot(allProducts, saleProducts);
        List<Items> randomBasket = robot.fillBasket(10);          // NEW variable
        CashRegister randomRegister = new CashRegister();        // NEW variable

        System.out.println("\n=== Random Basket ===");
        randomRegister.checkout(randomBasket, allProducts, saleProducts);
    }
    }

