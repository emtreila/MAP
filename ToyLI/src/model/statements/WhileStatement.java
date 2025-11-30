package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IStack;
import model.expressions.IExpression;
import model.types.BoolType;
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
        return state;
    }

    @Override
    public String toString() {
        return "while(" + this.expression.toString() + ") { " + this.statement.toString() + " }";
    }

}
