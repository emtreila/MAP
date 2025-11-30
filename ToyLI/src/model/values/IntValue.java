package model.values;

import model.types.IntType;
import model.types.IType;

public class IntValue implements IValue {
    private final int value;

    public IntValue(int val) {
        this.value = val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }


    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof IntValue) {
            return this.value == ((IntValue) another).getValue();
        }
        return false;
    }

    @Override
    public IntValue deepCopy() {
        return new IntValue(this.value);
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
