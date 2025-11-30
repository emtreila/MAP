package model.adts;

import exceptions.HeapException;
import model.values.IValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHeap implements IHeap<Integer, IValue> {
    private HashMap<Integer, IValue> heap;
    private Integer freeLocation;

    public MyHeap() {
        this.heap = new HashMap<>();
        this.freeLocation = 1;
    }

    public Integer getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public boolean isDefined(Integer key) {
        return this.heap.containsKey(key);
    }

    @Override
    public Integer add(IValue value) {
        this.heap.put(this.freeLocation, value);
        return this.freeLocation++;
    }

    @Override
    public void update(Integer key, IValue value) throws HeapException {
        if (!this.isDefined(key)) {
            throw new RuntimeException("Key " + key + " is not defined in the heap.");
        }
        this.heap.put(key, value);
    }

    @Override
    public IValue getValue(Integer key) throws HeapException {
        if (!this.isDefined(key)) {
            throw new HeapException("Key " + key + " is not defined in the heap.");
        }
        return this.heap.get(key);
    }

    @Override
    public List<Integer> getAllKeys() {
        return new ArrayList<>(this.heap.keySet());
    }

    @Override
    public List<IValue> getAllValues() throws HeapException {
        if (this.heap.isEmpty()) {
            throw new HeapException("The heap is empty.");
        }
        return new ArrayList<>(this.heap.values());
    }

    @Override
    public IHeap<Integer, IValue> deepCopy() {
        MyHeap newHeap = new MyHeap();
        newHeap.freeLocation = this.freeLocation;

        for (Map.Entry<Integer, IValue> entry : this.heap.entrySet()) {
            newHeap.heap.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return newHeap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Integer address : this.heap.keySet()) {
            sb.append(address).append("=");

            IValue value = this.heap.get(address);
            if (value == null) {
                sb.append("null");
            } else {
                sb.append(value.toString());
            }

            sb.append("; ");
        }

        if (!this.heap.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove last "; "
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return this.heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        this.heap = (HashMap<Integer, IValue>) newContent;
    }


}
