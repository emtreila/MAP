package repo;

import model.Vegetable;

public class Repo implements InterfaceRepo {
    private Vegetable[] vegetables;
    private int cnt;

    public Repo() {
        this.vegetables = new Vegetable[10];
        this.cnt = 0;
    }

    @Override
    public void add(Vegetable vegetable) {
        if (this.cnt >= this.vegetables.length) {
            Vegetable[] newVeg = new Vegetable[this.vegetables.length * 2];
            System.arraycopy(this.vegetables, 0, newVeg, 0, this.vegetables.length);
            this.vegetables = newVeg;
        }
        this.vegetables[this.cnt++] = vegetable;

    }

    @Override
    public void remove(Vegetable vegetable) throws Exception {
        if (this.cnt == 0) {
            throw new Exception("Array is empty!");
        }
        for (int i = 0; i < this.cnt; i++) {
            if (this.vegetables[i].getClass() == vegetable.getClass() && this.vegetables[i].getWeight() == vegetable.getWeight()) {
                for (int j = i; j < this.cnt - 1; j++) {
                    this.vegetables[j] = vegetables[j + 1];
                }
                this.cnt--;
                return;
            }
        }
        throw new Exception("Vegetable not found!");
    }

    @Override
    public Vegetable[] getAll() {
        Vegetable[] newVeg = new Vegetable[this.cnt];
        System.arraycopy(this.vegetables, 0, newVeg, 0, this.cnt);
        return newVeg;
    }
}
