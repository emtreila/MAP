package model.Values;

import model.Types.IType;
import model.Types.StringType;

public class StringValue implements IValue {
    private String value;

    public StringValue(String val) {
        this.value = val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof StringValue) {
            return this.value.equals(((StringValue) another).getValue());
        }
        return false;
    }

    @Override
    public IValue clone() {
        return new StringValue(this.value);
    }
}
