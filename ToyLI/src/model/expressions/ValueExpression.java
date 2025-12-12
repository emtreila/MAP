package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.types.IType;
import model.values.IValue;

import java.lang.reflect.Type;

public class ValueExpression implements IExpression {

    IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap<Integer, IValue> heap) throws ExpressionEvaluationException {
        if (symTable == null) {
            throw new ExpressionEvaluationException("Symbol table is null!");
        }
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
    
    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws ExpressionEvaluationException {
        return this.value.getType();
    }
}
