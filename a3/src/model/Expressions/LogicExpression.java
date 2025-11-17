package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Types.BoolType;
import model.Values.BoolValue;
import model.Values.IValue;

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
        IValue val1, val2;
        val1 = this.exp1.eval(symTable);
        if (val1.getType().equals(new BoolType())) {
            val2 = this.exp2.eval(symTable);
            if (val2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) val1;
                BoolValue bool2 = (BoolValue) val2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.operation, "&&")) {
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.operation, "||")) {
                    return new BoolValue(b1 || b2);
                }
            } else {
                throw new ExpressionEvaluationException("Second operand is not a boolean!");
            }
        } else {
            throw new ExpressionEvaluationException("First operand is not a boolean!");
        }
        return null;
    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }

}


