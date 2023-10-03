package Candy;

public class ChupaChups extends Candy{
    private ChupaChupsColor color;

    public ChupaChups(String name, double weight, double amountOfSugar, ChupaChupsColor color) {
        super(name, weight, amountOfSugar);
        this.color = color;
    }

    public ChupaChupsColor getColor() {
        return color;
    }

    public void setColor(ChupaChupsColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ChupaChups{" +
                "color=" + color +
                "} " + super.toString();
    }
}
