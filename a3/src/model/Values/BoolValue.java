package model.values;


import model.types.BoolType;

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

    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolValue) {
            return this.value == ((BoolValue) another).getValue();
        }
        return false;
    }
    
    @Override
    public IValue clone() {
        return new BoolValue(this.value);
    }
}
