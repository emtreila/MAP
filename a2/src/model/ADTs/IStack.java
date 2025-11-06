package model.ADTs;

import exceptions.StackException;

import java.util.List;

public interface IStack<T> {
    void push(T element);

    T pop() throws StackException;

    T top() throws StackException;

    boolean isEmpty();

    int size();
    
    List<T> getAll();

    List<T> getReversedList();

}