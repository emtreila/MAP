package model.adts;

import exceptions.DictionaryException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDictionary<Key, Value> {
    boolean isDefined(Key key);

    void add(Key key, Value value);

    void update(Key key, Value value);

    void remove(Key key);

    Value getValue(Key key) throws DictionaryException;

    List<Key> getAllKeys();

    List<Value> getAllValues();

    public IDictionary<Key, Value> deepCopy();

    Map<Key, Value> getContent();


}
