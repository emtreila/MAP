package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

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
        IValue value1 = this.expr1.eval(symTable);
        if (!value1.getType().equals(new IntType()))
            throw new ExpressionEvaluationException("First operand is not an integer.");

        IValue value2 = this.expr2.eval(symTable);
        if (!value2.getType().equals(new IntType()))
            throw new ExpressionEvaluationException("Second operand is not an integer.");

        int v1 = ((IntValue) value1).getValue();
        int v2 = ((IntValue) value2).getValue();

        return switch (this.operator) {
            case LESS -> new BoolValue(v1 < v2);
            case LESS_EQUAL -> new BoolValue(v1 <= v2);
            case EQUAL -> new BoolValue(v1 == v2);
            case NOT_EQUAL -> new BoolValue(v1 != v2);
            case GREATER -> new BoolValue(v1 > v2);
            case GREATER_EQUAL -> new BoolValue(v1 >= v2);
            default -> throw new ExpressionEvaluationException("Undefined relational operator: " + this.operator);
        };
    }


    @Override
    public String toString() {
        return this.expr1.toString() + " " + this.operator + " " + this.expr2.toString();
    }
}
