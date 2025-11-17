package model.ADTs;


import exceptions.DictionaryException;

import java.util.*;

public class MyDictionary<Key, Value> implements IDictionary<Key, Value> {

    private Map<Key, Value> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public boolean isDefined(Key key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public void add(Key key, Value value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void update(Key key, Value value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void remove(Key key) {
        if (!this.isDefined(key)) {
            throw new DictionaryException("Key " + key + " is not defined in the dictionary.");
        }
        this.dictionary.remove(key);
    }

    @Override
    public Value getValue(Key key) throws DictionaryException {
        if (!this.isDefined(key)) {
            throw new DictionaryException("Key " + key + " is not defined in the dictionary.");
        }
        return this.dictionary.get(key);
    }

    @Override
    public List<Key> getAllKeys() {
        return new ArrayList<>(this.dictionary.keySet());
    }

    @Override
    public List<Value> getAllValues() {
        if (this.dictionary.isEmpty()) {
            throw new DictionaryException("The dictionary is empty.");
        }
        return new ArrayList<>(this.dictionary.values());

    }

    @Override
    public IDictionary<Key, Value> deepCopy() {
        return new MyDictionary<Key, Value>() {{
            for (Map.Entry<Key, Value> entry : dictionary.entrySet()) {
                //this.add(entry.getKey(), entry.getValue().clone());
                this.add(entry.getKey(), entry.getValue());
            }
        }};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Key key : this.dictionary.keySet()) {
            sb.append(key.toString()).append("=");
            Value value = this.dictionary.get(key);
            if (value instanceof java.io.BufferedReader) {
                sb.append("BufferedReader(").append(key).append(")");
            } else {
                sb.append(value.toString());
            }
            sb.append("; ");
        }
        if (!this.dictionary.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove last "; "
        }
        sb.append("}");
        return sb.toString();
    }

}