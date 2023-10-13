package Bulb;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class IncandescentLamp extends Bulb {
    private double operatingTime;
    private final static double CONST1 = 1.0;

    public IncandescentLamp(String manufacturer, double power, double operatingTime) {
        super(manufacturer, power);
        this.operatingTime = operatingTime;
    }

    @Override
    public double countPrice() throws NumberFormatException {
        double result = getPower() * CONST1 * operatingTime;
        return BigDecimal
                .valueOf(result)
                .setScale(2)
                .doubleValue();
    }

    public double getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(double operatingTime) {
        this.operatingTime = operatingTime;
    }

    @Override
    public String toString() {
        return "IncandescentLamp{" +
                "operatingTime=" + operatingTime +
                super.toString()+ "};";
    }
}
