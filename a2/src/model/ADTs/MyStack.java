package model.ADTs;

import exceptions.StackException;

import java.util.*;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() throws StackException {
        if (this.stack.empty()) {
            throw new StackException("Can't pop! Stack is empty!");
        }
        return this.stack.pop();
    }

    @Override
    public T top() throws StackException {
        if (this.stack.empty()) {
            throw new StackException("Can't top! Stack is empty");
        }
        return this.stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.empty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(this.stack);
    }

    @Override
    public List<T> getReversedList() {
        List<T> reversed = new ArrayList<>(this.stack);
        Collections.reverse(reversed);
        return reversed;
    }

    @Override
    public String toString() {
        return getReversedList().toString();
    }


}


