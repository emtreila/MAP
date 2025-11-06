package model;

public class Ardei implements Vegetable {
    private double weight;

    public Ardei(double weight) {
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
        return "Ardei | weight= " + this.weight;
    }
}
