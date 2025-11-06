package model.ADTs;

import exceptions.DictionaryException;

import java.util.List;
import java.util.Map;

public interface IDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void add(TKey key, TValue value);

    void update(TKey key, TValue value);

    void remove(TKey key);

    TValue getValue(TKey key) throws DictionaryException;

    List<TKey> getAllKeys();

    List<TValue> getAllValues();

    public IDictionary<TKey, TValue> deepCopy();


}
