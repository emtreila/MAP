package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue {
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return this.address;
    }

    public IType getLocationType() {
        return this.locationType;
    }

    @Override
    public IType getType() {
        return new RefType(this.locationType);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(this.address, this.locationType);
    }

    @Override
    public String toString() {
        return "Ref(" + this.address + ", " + this.locationType + ")";
    }
}
