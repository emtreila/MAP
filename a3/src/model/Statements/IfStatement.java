package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.ADTs.IStack;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Types.BoolType;
import model.Values.BoolValue;
import model.Values.IValue;

public class IfStatement implements IStatement {
    private IExpression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression exp, IStatement thenStatement, IStatement elseStatement) {
        this.expression = exp;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "if ("
                + this.expression.toString()
                + ")"
                + "then {"
                + this.thenStatement.toString()
                + "} else {"
                + this.elseStatement.toString()
                + "}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExeStack();
        IDictionary<String, IValue> symTable = state.getSymTable();
        IValue value = this.expression.eval(symTable);

        if (!value.getType().equals(new BoolType()))
            throw new StatementExecutionException("Conditional expression is not boolean!");
        BoolValue conditionValue = (BoolValue) value;
        if (conditionValue.getValue())
            exeStack.push(this.thenStatement);
        else
            exeStack.push(this.elseStatement);
        return state;
    }
}
