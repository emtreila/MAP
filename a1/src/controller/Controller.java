package controller;

import model.Vegetable;
import repo.InterfaceRepo;
import repo.Repo;


public class Controller {

    private InterfaceRepo repo;

    public Controller() {
        this.repo = new Repo();
    }

    public void add(Vegetable vegetable) {
        this.repo.add(vegetable);
    }

    public void remove(Vegetable vegetable) throws Exception {
        this.repo.remove(vegetable);
    }

    public Vegetable[] getAll() {
        return this.repo.getAll();
    }

    public Vegetable[] getHeavyVeggie() {
        Vegetable[] vegetables = this.repo.getAll();
        Vegetable[] temp = new Vegetable[vegetables.length];
        int count = 0;
        for (Vegetable v : vegetables) {
            if (v != null && v.getWeight() > 0.2) {
                temp[count++] = v;
            }
        }
        Vegetable[] result = new Vegetable[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }


}
