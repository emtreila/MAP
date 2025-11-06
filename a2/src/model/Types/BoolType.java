package model.Types;

import model.Values.BoolValue;
import model.Values.IValue;

public class BoolType implements IType {
    
    public BoolType(){}

    @Override
    public boolean equals(Object anotherType) {
        return anotherType instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }
    
    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }
}
