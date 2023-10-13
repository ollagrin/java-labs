package Bulb;

import java.math.BigDecimal;

public class LEDLamp extends Bulb {
    private int numberOfBulbs;
    private final static double CONST2 = 2;

    public LEDLamp(String manufacturer, double power, int numberOfBulbs) {
        super(manufacturer, power);
        this.numberOfBulbs = numberOfBulbs;
    }

    @Override
    public double countPrice() throws NumberFormatException {
        double result = getPower() * numberOfBulbs / CONST2;
        return BigDecimal
                .valueOf(result)
                .setScale(2)
                .doubleValue();
    }

    public int getNumberOfBulbs() {
        return numberOfBulbs;
    }

    public void setNumberOfBulbs(int numberOfBulbs) {
        this.numberOfBulbs = numberOfBulbs;
    }

    @Override
    public String toString() {
        return "LEDLamp{" +
                "numberOfBulbs=" + numberOfBulbs +
                " " + super.toString() + "};";
    }
}
