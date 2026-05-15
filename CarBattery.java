package Assignment;

public class CarBattery extends StockItem {

    private int capacityAh;
    private String voltage;

    public CarBattery(String stockCode, int quantity, double price,
                      int capacityAh, String voltage) {
        super(stockCode, quantity, price);
        this.capacityAh = capacityAh;
        this.voltage = voltage;
    }


    public int getCapacityAh() {
        return capacityAh;
    }

    public void setCapacityAh(int capacityAh) {
        this.capacityAh = capacityAh;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    @Override
    public String getStockName() {
        return "Car Battery";
    }

    @Override
    public String getStockDescription() {
        return voltage + " " + capacityAh + "Ah Battery";
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nVoltage: " + voltage +
                "\nCapacity: " + capacityAh + "Ah";
    }
}