package model.ADTs;


import exceptions.DictionaryException;

import java.util.*;

public class MyDictionary<TKey, TValue> implements IDictionary<TKey, TValue> {

    private Map<TKey, TValue> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public boolean isDefined(TKey key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public void add(TKey key, TValue value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void update(TKey key, TValue value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void remove(TKey key) {
        this.dictionary.remove(key);
    }

    @Override
    public TValue getValue(TKey key) throws DictionaryException {
        if (!this.isDefined(key)) {
            throw new DictionaryException("Key " + key + " is not defined in the dictionary.");
        }
        return this.dictionary.get(key);
    }

    @Override
    public List<TKey> getAllKeys() {
        return new ArrayList<>(this.dictionary.keySet());
    }

    @Override
    public List<TValue> getAllValues() {
        return new ArrayList<>(this.dictionary.values());
        
    }

    @Override
    public IDictionary<TKey, TValue> deepCopy(){
        IDictionary<TKey, TValue> copyDict = new MyDictionary<>();
        for (TKey key : this.getAllKeys()) {
            copyDict.add(key, this.getValue(key));
        }
        return copyDict;
    }

    @Override
    public String toString() {
        return this.dictionary.toString();
    }
}