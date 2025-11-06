package model.Values;


import model.Types.BoolType;

public class BoolValue implements IValue {

    private final boolean value;

    public BoolValue(boolean val) {
        this.value = val;
    }

    @Override
    public BoolType getType() {
        return new BoolType();
    }


    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public boolean getValue() {
        return this.value;
    }
}
