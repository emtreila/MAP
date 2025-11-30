package model.types;

import model.values.IntValue;
import model.values.IValue;

public class IntType implements IType {

    public IntType() {
    }

    @Override
    public boolean equals(Object anotherType) {
        return anotherType instanceof IntType; // check if anotherType is of type IntType
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
