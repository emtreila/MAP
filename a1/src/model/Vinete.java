package model;

public class Vinete implements Vegetable {
    private double weight;

    public Vinete(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Vinete | weight= " + this.weight;
    }


}
