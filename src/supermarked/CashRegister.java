package supermarked;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class CashRegister {

    // Inner class to store grouped item info
    private static class ItemSummary {
        String name;
        double normalPrice;   // original price
        double unitPrice;     // price used in checkout (sale price if on sale)
        double totalAmount;   // total amount bought
        boolean onSale;

        public ItemSummary(String name, double normalPrice, double unitPrice, double totalAmount, boolean onSale) {
            this.name = name;
            this.normalPrice = normalPrice;
            this.unitPrice = unitPrice;
            this.totalAmount = totalAmount;
            this.onSale = onSale;
        }
    }

    // Checkout method
    public void checkout(List<Items> basket, Map<String, Items> allProducts, Map<String, Items> saleProducts) {

        //  group items by EAN code
        Map<String, ItemSummary> summaryMap = new HashMap<>();

        for (Items item : basket) {
            String ean = item.getItemEANCode();

            double normalPrice = allProducts.containsKey(ean) ? allProducts.get(ean).getPrice() : item.getPrice();
            double checkoutPrice = saleProducts.containsKey(ean) ? saleProducts.get(ean).getPrice() : normalPrice;
            boolean onSale = saleProducts.containsKey(ean);

            if (summaryMap.containsKey(ean)) {
                ItemSummary summary = summaryMap.get(ean);
                summary.totalAmount += item.getAmount();
            } else {
                summaryMap.put(ean, new ItemSummary(
                        item.getName(),
                        normalPrice,
                        checkoutPrice,
                        item.getAmount(),
                        onSale
                ));
            }
        }

        // Step 2: calculate totals and VAT
        double total = 0;
        double totalVat = 0;
        double vatRate = 0.25; // 25% VAT

        System.out.println("==== RECEIPT ====");
        for (ItemSummary summary : summaryMap.values()) {
            double itemTotal = summary.unitPrice * summary.totalAmount;
            total += itemTotal;
            totalVat += itemTotal * vatRate;

            // Print item line
            System.out.printf("%s: %.2f x %.2f = %.2f\n",
                    summary.name,
                    summary.unitPrice,
                    summary.totalAmount,
                    itemTotal);

            // Print discount if on sale
            if (summary.onSale) {
                double discount = (summary.normalPrice - summary.unitPrice) * summary.totalAmount;
                System.out.printf("   Discount: -%.2f\n", discount);
            }
        }

        System.out.println("-------------------------");
        System.out.printf("TOTAL: %.2f\n", total);
        System.out.printf("VAT (25%%): %.2f\n", totalVat);
    }
    }
