package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IDictionary;
import model.adts.IStack;
import model.expressions.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStatement implements IStatement {
    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IValue value = this.expression.eval(state.getSymTable(), state.getHeap());
        IStack<IStatement> stack = state.getExeStack();

        if (!value.getType().equals(new BoolType()))
            throw new StatementExecutionException("While condition is not boolean: " + value);

        BoolValue boolValue = (BoolValue) value;
        if (boolValue.getValue()) {
            stack.push(this); // push the while statement again for the next iteration
            stack.push(this.statement); // push the body of the while loop
        }
        return null;
    }

    @Override
    public String toString() {
        return "while(" + this.expression.toString() + ") { " + this.statement.toString() + " }";
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        IType typeExpr = this.expression.typeCheck(typeEnv);
        if (!typeExpr.equals(new BoolType())) {
            throw new StatementExecutionException("The condition of WHILE isnt of type bool.");
        }
        this.statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

}
