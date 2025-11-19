package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType{
    
    @Override
    public boolean equals(Object anotherType){
        return anotherType instanceof StringType;
    }
    
    @Override
    public IValue defaultValue(){
        return new StringValue("");
    }
    
    @Override
    public String toString(){
        return "string";
    }
}
