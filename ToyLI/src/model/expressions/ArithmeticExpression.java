package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.statements.NewStatement;
import model.types.IType;
import model.types.IntType;
import model.values.IntValue;
import model.values.IValue;

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
    public IValue eval(IDictionary<String, IValue> symTable, IHeap<Integer, IValue> heap) throws ExpressionEvaluationException {
        IValue val1 = this.exp1.eval(symTable, heap);

        if (!val1.getType().equals(new IntType()))
            throw new ExpressionEvaluationException("First operand is not an integer!");

        IValue val2 = this.exp2.eval(symTable, heap);

        if (!val2.getType().equals(new IntType()))
            throw new ExpressionEvaluationException("Second operand is not an integer!");

        int n1 = ((IntValue) val1).getValue();
        int n2 = ((IntValue) val2).getValue();

        return switch (this.operation) {
            case ADD -> new IntValue(n1 + n2);
            case SUB -> new IntValue(n1 - n2);
            case MUL -> new IntValue(n1 * n2);
            case DIV -> {
                if (n2 == 0)
                    throw new ExpressionEvaluationException("Division by 0!");
                yield new IntValue(n1 / n2);
            }
            default -> throw new ExpressionEvaluationException("Invalid arithmetic operation: " + this.operation);
        };
    }


    @Override
    public String toString() {
        return this.exp1.toString() + " " + this.operation + " " + this.exp2.toString();
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws ExpressionEvaluationException {
        IType type1, type2;
        type1 = this.exp1.typeCheck(typeEnv);
        type2 = this.exp2.typeCheck(typeEnv);

        if (!type1.equals(new IntType())) {
            throw new ExpressionEvaluationException("First operand is not an integer!");
        }
        if (!type2.equals(new IntType())) {
            throw new ExpressionEvaluationException("Second operand is not an integer!");
        }
        return new IntType();
    }
}


