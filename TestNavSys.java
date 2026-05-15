package Assignment;


public class TestNavSys {

    public static void main(String[] args) {


        System.out.println("=== Task 1: Creating NavSys stock ===");
        NavSys nav = new NavSys("NS101", 10, 99.99);
        System.out.println(nav);

        System.out.println("\n=== Task 2: Adding 10 more units ===");
        nav.addStock(10);
        System.out.println(nav);

        System.out.println("\n=== Task 3: Selling 2 units ===");
        nav.sellStock(2);
        System.out.println(nav);

        System.out.println("\n=== Task 4: Setting new price to 100.99 ===");
        nav.setPrice(100.99);
        System.out.println(nav);

        System.out.println("\n=== Task 5: Adding 0 units - should give error ===");
        nav.addStock(0);
    }
}