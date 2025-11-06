package model;

public class Rosii implements Vegetable {
    private double weight;

    public Rosii(double weight) {
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
        return "Rosii | weight= " + this.weight;
    }
}
