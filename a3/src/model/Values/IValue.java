package model.Values;

import model.Types.IType;

public interface IValue {
    IType getType();
    IValue clone();
}
