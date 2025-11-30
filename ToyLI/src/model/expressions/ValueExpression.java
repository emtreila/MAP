package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.values.IValue;

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
}
