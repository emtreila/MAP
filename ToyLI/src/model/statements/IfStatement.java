package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.adts.IStack;
import model.expressions.IExpression;
import model.ProgramState;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

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
        IValue value = this.expression.eval(symTable, state.getHeap());

        if (!value.getType().equals(new BoolType()))
            throw new StatementExecutionException("Conditional expression is not boolean!");
        BoolValue conditionValue = (BoolValue) value;
        if (conditionValue.getValue())
            exeStack.push(this.thenStatement);
        else
            exeStack.push(this.elseStatement);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        IType typeExpr = this.expression.typeCheck(typeEnv);
        if (!typeExpr.equals(new BoolType())) {
            throw new StatementExecutionException("The condition of IF isnt of  type bool");
        }
        this.thenStatement.typeCheck(typeEnv.deepCopy());
        this.elseStatement.typeCheck(typeEnv.deepCopy());
        return typeEnv;

    }
}
