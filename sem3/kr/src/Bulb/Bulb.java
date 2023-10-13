package Bulb;

import java.util.Comparator;

public abstract class Bulb implements Comparable<Bulb> {
    private String manufacturer;
    private double power;

    public Bulb(String manufacturer, double power) {
        this.manufacturer = manufacturer;
        this.power = power;
    }

    public abstract double countPrice();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public int compareTo(Bulb o) {
        return Comparator.comparing(Bulb::countPrice).compare(this, o);
    }

    @Override
    public String toString() {
        return "Bulb{" +
                "manufacturer='" + manufacturer + '\'' +
                ", power=" + power +
                '}';
    }
}
