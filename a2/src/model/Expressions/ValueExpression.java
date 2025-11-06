package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Values.IValue;

public class ValueExpression implements IExpression {

    IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException {
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
