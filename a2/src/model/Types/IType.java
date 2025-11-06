package model.Types;

import model.Values.IValue;

public interface IType {
    boolean equals(Object anotherType);

    IValue defaultValue();
}
