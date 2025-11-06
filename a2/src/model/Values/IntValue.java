package model.Values;

import model.Types.IntType;
import model.Types.IType;

public class IntValue implements IValue {
    private final int value;

    public IntValue(int val) {
        this.value = val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }


    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public int getValue() {
        return this.value;
    }
    
    


}
