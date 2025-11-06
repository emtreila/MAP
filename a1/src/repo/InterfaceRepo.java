package repo;


import model.Vegetable;

public interface InterfaceRepo {
    void add(Vegetable vegetable);

    void remove(Vegetable vegetable) throws Exception;

    Vegetable[] getAll();
}
