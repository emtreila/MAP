package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Types.IntType;
import model.Values.IntValue;
import model.Values.IValue;

public class ArithmeticExpression implements IExpression {

    private IExpression exp1; 
    private IExpression exp2;
    private AOperator operation;

    public ArithmeticExpression(IExpression exp1, IExpression exp2, AOperator op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operation = op;
    }
    

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException {
        IValue val1, val2;
        val1 = this.exp1.eval(symTable);
        if (val1.getType().equals(new IntType())) {
            val2 = this.exp2.eval(symTable);
            if (val2.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) val1;
                IntValue int2 = (IntValue) val2;
                int n1, n2;
                n1 = int1.getValue();
                n2 = int2.getValue();

                if (this.operation == AOperator.ADD) {
                    return new IntValue(n1 + n2);
                } else if (this.operation == AOperator.SUB) {
                    return new IntValue(n1 - n2);
                } else if (this.operation == AOperator.MUL) {
                    return new IntValue(n1 * n2);
                } else if (this.operation == AOperator.DIV) {
                    if (n2 == 0) {
                        throw new ExpressionEvaluationException("Division by 0!");
                    } else {
                        return new IntValue(n1 / n2);
                    }
                } else {
                    throw new ExpressionEvaluationException("Invalid operation!");
                }
            } else {
                throw new ExpressionEvaluationException("Second operand is not an integer!");
            }
        } else {
            throw new ExpressionEvaluationException("First operand is not an integer!");
        }

    }

    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }
}


