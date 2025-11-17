package model.ADTs;

import exceptions.ListException;

import java.util.List;

public interface IList<T> {

    void add(T elem);

    void removeFromPos(int position) throws ListException;

    T getElem(int position) throws ListException;

    void setElem(int position, T newElem) throws ListException;

    List<T> getAll();
}
