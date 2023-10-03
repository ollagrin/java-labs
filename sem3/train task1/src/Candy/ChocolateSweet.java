package Candy;

public class ChocolateSweet extends Candy{
    public ChocolateSweet(String name, double weight, double amountOfSugar) {
        super(name, weight, amountOfSugar);
    }

    @Override
    public String toString() {
        return "ChocolateSweet{} " + super.toString();
    }
}
