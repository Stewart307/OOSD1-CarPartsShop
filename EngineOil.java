package Assignment;

public class EngineOil extends StockItem {

    private String viscosity;     // for example "5W-30"
    private double volumeLitres;  // for example 5.0 meaning 5 litres

    public EngineOil(String stockCode, int quantity, double price,
                     String viscosity, double volumeLitres) {
        super(stockCode, quantity, price);
        this.viscosity = viscosity;
        this.volumeLitres = volumeLitres;
    }

    public String getViscosity() {
        return viscosity;
    }

    public void setViscosity(String viscosity) {
        this.viscosity = viscosity;
    }

    public double getVolumeLitres() {
        return volumeLitres;
    }

    public void setVolumeLitres(double volumeLitres) {
        this.volumeLitres = volumeLitres;
    }

    @Override
    public String getStockName() {
        return "Engine Oil";
    }

    @Override
    public String getStockDescription() {
        return viscosity + " " + volumeLitres + "L";
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nViscosity: " + viscosity +
                "\nVolume: " + volumeLitres + "L";
    }
}