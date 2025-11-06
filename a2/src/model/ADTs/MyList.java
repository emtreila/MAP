package model.ADTs;

import exceptions.ListException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {

    private List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);
    }

    @Override
    public void removeFromPos(int position) throws ListException {
        try {
            this.list.remove(position);
        } catch (IndexOutOfBoundsException e) {
            throw new ListException("Invalid position!");
        }
    }

    @Override
    public boolean removeCheck(T elem) {
        return this.list.remove(elem);
    }

    @Override
    public T getElem(int position) throws ListException {
        try {
            return this.list.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw new ListException("Invalid position!");
        }
    }

    @Override
    public void setElem(int position, T newElem) throws ListException {
        try {
            this.list.set(position, newElem);
        } catch (IndexOutOfBoundsException e) {
            throw new ListException("Invalid index!");
        }
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(this.list);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
