package supermarked;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BasketRobot {
    private Map<String, Items> allProducts;
    private Map<String, Items> saleProducts;
    private Random random;

    public BasketRobot(Map<String, Items> allProducts, Map<String, Items> saleProducts) {
        this.allProducts = allProducts;
        this.saleProducts = saleProducts;
        this.random = new Random();
    }

    // Generate a random basket
    public List<Items> fillBasket(int maxItems) {
        List<Items> basket = new ArrayList<>();

        List<Items> productList = new ArrayList<>(allProducts.values());

        for (int i = 0; i < maxItems; i++) {
            // Pick a random product
            Items product = productList.get(random.nextInt(productList.size()));

            // Random amount: 1-3 for pieces, or 0.1-1 for weighted items
            double amount = product.getUnitType().equals("pcs") ?
                    1 + random.nextInt(3) : 0.1 + random.nextDouble() * 0.9;

            // Check if it’s on sale
            boolean isSale = saleProducts.containsKey(product.getItemEANCode());

            basket.add(new Items(
                    product.getItemEANCode(),
                    product.getName(),
                    isSale ? saleProducts.get(product.getItemEANCode()).getPrice() : product.getPrice(),
                    amount,
                    product.getUnitType(),
                    isSale
            ));
        }

        return basket;
    }
}
