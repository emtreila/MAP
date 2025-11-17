package model.Types;

import model.Values.IValue;
import model.Values.StringValue;

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
