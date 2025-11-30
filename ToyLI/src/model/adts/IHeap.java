package model.adts;

import exceptions.HeapException;

import java.util.List;
import java.util.Map;

public interface IHeap<Key, Value> {
    Integer getFreeLocation();

    boolean isDefined(Key key);

    Integer add(Value value); // returns the address where the value was added

    void update(Key key, Value value) throws HeapException;

    Value getValue(Key key) throws HeapException;

    List<Key> getAllKeys();

    List<Value> getAllValues() throws HeapException;

    IHeap<Key, Value> deepCopy();

    Map<Key, Value> getContent();

    void setContent(Map<Key, Value> newContent);

}
