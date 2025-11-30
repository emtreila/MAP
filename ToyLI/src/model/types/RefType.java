package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType {
    private IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(Object anotherType) {
        if (anotherType instanceof RefType) {
            return this.inner.equals(((RefType) anotherType).getInner());
        } else {
            return false;
        }
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public String toString() {
        return "Ref(" + this.inner.toString() + ")";
    }
}
