package Assignment;

import java.util.Scanner;

public class TestPolymorphism {

    public static void itemInstance(StockItem s) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Now working with: "
                + s.getStockName() + " (" + s.getStockCode() + ") ---");
        System.out.println("Current information:");
        System.out.println(s);

        System.out.print("\nHow many units do you want to add? ");
        int toAdd = sc.nextInt();
        s.addStock(toAdd);
        System.out.println("After adding stock:");
        System.out.println(s);

        System.out.print("\nHow many units do you want to sell? ");
        int toSell = sc.nextInt();
        boolean sold = s.sellStock(toSell);
        if (sold) {
            System.out.println("Sale was successful.");
        }
        System.out.println("After selling stock:");
        System.out.println(s);

        System.out.print("\nWhat is the new price (without VAT)? ");
        double newPrice = sc.nextDouble();
        s.setPrice(newPrice);
        System.out.println("After price change:");
        System.out.println(s);
    }

    public static void main(String[] args) {

        StockItem[] s = new StockItem[4];

        s[0] = new NavSys("NS101", 10, 99.99);
        s[1] = new Tyre("TY201", 15, 49.99, "205/55R16", "Michelin");
        s[2] = new EngineOil("EO301", 20, 12.99, "5W-30", 5.0);
        s[3] = new CarBattery("CB401", 8, 79.99, 60, "12V");

        for (StockItem item : s) {
            itemInstance(item);
        }
    }
}