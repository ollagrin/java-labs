/*
 * class Candy
 *
 * Version 04.12.2021
 *
 * Author Volha Hrynko
 */

package Candy;

public abstract class Candy {

    private String name;
    private double weight;
    private double amountOfSugar;

    public Candy(String name, double weight, double amountOfSugar) {
        this.name = name;
        this.weight = weight;
        this.amountOfSugar = amountOfSugar;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAmountOfSugar() {
        return amountOfSugar;
    }

    public void setAmountOfSugar(double amountOfSugar) {
        this.amountOfSugar = amountOfSugar;
    }
}
