package Assignment;

public class Tyre extends StockItem {

    private String tyreSize;  
    private String brand;     

    public Tyre(String stockCode, int quantity, double price,
                String tyreSize, String brand) {
        super(stockCode, quantity, price);
        this.tyreSize = tyreSize;
        this.brand = brand;
    }

    public String getTyreSize() {
        return tyreSize;
    }

    public void setTyreSize(String tyreSize) {
        this.tyreSize = tyreSize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getStockName() {
        return "Tyre";
    }

    @Override
    public String getStockDescription() {
        return brand + " " + tyreSize;
    }
    
    @Override
    public String toString() {
        return super.toString() +
                "\nTyre Size: " + tyreSize +
                "\nBrand: " + brand;
    }
}
