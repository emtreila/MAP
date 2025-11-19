package model.types;

import model.values.IValue;

public interface IType {
    boolean equals(Object anotherType);

    IValue defaultValue();
}
