package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.values.IValue;

public class VariableExpression implements IExpression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap<Integer, IValue> heap) throws ExpressionEvaluationException {
        if (symTable == null)
            throw new ExpressionEvaluationException("Symbol table is null!");

        IValue result = symTable.getValue(this.name);
        if (result == null)
            throw new ExpressionEvaluationException("Variable " + this.name + " is not defined!");

        return result;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
