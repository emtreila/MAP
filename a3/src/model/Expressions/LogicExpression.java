package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

import java.util.Objects;

public class LogicExpression implements IExpression {

    private IExpression exp1;
    private IExpression exp2;
    private String operation;


    public LogicExpression(IExpression exp1, IExpression exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = op;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException {
        IValue val1 = this.exp1.eval(symTable);
        if (!val1.getType().equals(new BoolType()))
            throw new ExpressionEvaluationException("First operand is not a boolean!");

        IValue val2 = this.exp2.eval(symTable);
        if (!val2.getType().equals(new BoolType()))
            throw new ExpressionEvaluationException("Second operand is not a boolean!");

        boolean b1 = ((BoolValue) val1).getValue();
        boolean b2 = ((BoolValue) val2).getValue();

        return switch (this.operation) {
            case "&&" -> new BoolValue(b1 && b2);
            case "||" -> new BoolValue(b1 || b2);
            default -> throw new ExpressionEvaluationException("Undefined boolean operator: " + this.operation);
        };
    }


    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }

}


