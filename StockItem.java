package Assignment;


public class StockItem {


    private final String stockCode;
    private int quantity;
    private double price;


    private static final double VAT_RATE = 17.5;

    public StockItem(String stockCode, int quantity, double price) {
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.price = price;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStockName() {
        return "Unknown Stock Name";
    }

    public String getStockDescription() {
        return "Unknown Stock Description";
    }

    public double getVAT() {
        return VAT_RATE;
    }

    public double getPriceWithoutVAT() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getPriceWithVAT() {
        return price + (price * VAT_RATE / 100);
    }

    public void addStock(int amount) {
        if (amount < 1) {

            System.out.println("Error: Increased item must be greater than or equal to one");
        } else if (quantity + amount > 100) {

            System.out.println("Error: Stock cannot exceed 100 units");
        } else {

            quantity += amount;
        }
    }


    public boolean sellStock(int amount) {
        if (amount < 1) {
            System.out.println("Error: Sold amount must be greater than or equal to one");
            return false;
        }
        if (amount <= quantity) {
            quantity -= amount;
            return true;
        } else {
            System.out.println("Error: Not enough stock available");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Stock Type: " + getStockName() + "\n" +
                "Description: " + getStockDescription() + "\n" +
                "Stock Code: " + getStockCode() + "\n" +
                "Price Without VAT: " + getPriceWithoutVAT() + "\n" +
                "Price With VAT: " + getPriceWithVAT() + "\n" +
                "Total unit in stock: " + getQuantity();
    }
}