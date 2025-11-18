package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Types.IntType;
import model.Values.BoolValue;
import model.Values.IValue;
import model.Values.IntValue;

import java.util.Objects;

public class RelationalExpression implements IExpression {
    private IExpression expr1;
    private IExpression expr2;
    private ROperator operator;

    public RelationalExpression(IExpression expr1, IExpression expr2, ROperator operator) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }


    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException {
        IValue value1, value2;
        value1 = this.expr1.eval(symTable);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expr2.eval(symTable);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getValue();
                v2 = val2.getValue();
                if (this.operator == ROperator.LESS)
                    return new BoolValue(v1 < v2);
                else if (this.operator == ROperator.LESS_EQUAL)
                    return new BoolValue(v1 <= v2);
                else if (this.operator == ROperator.EQUAL)
                    return new BoolValue(v1 == v2);
                else if (this.operator == ROperator.NOT_EQUAL)
                    return new BoolValue(v1 != v2);
                else if (this.operator == ROperator.GREATER)
                    return new BoolValue(v1 > v2);
                else if (this.operator == ROperator.GREATER_EQUAL)
                    return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand in not an integer.");
        return null;
    }

    @Override
    public String toString() {
        return this.expr1.toString() + " " + this.operator + " " + this.expr2.toString();
    }
}
