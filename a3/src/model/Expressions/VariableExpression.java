package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Values.IValue;

public class VariableExpression implements IExpression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException {
        if (!symTable.isDefined(this.name)) {
            throw new ExpressionEvaluationException("Variable " + this.name + " is not defined!");
        }
        return symTable.getValue(this.name);
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
