package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.expressions.IExpression;
import model.ProgramState;
import model.types.IType;
import model.values.IValue;


public class AssignmentStatement implements IStatement {
    private String name;
    private IExpression expression;

    public AssignmentStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.name + "=" + this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();

        if (!symTable.isDefined(this.name)) {
            throw new StatementExecutionException("The used variable " + this.name + " was not declared before.");
        }
        IValue value = this.expression.eval(symTable, state.getHeap());
        IType typeName = (symTable.getValue(this.name)).getType();
        if (!value.getType().equals(typeName)) {
            throw new StatementExecutionException("Declared type of variable " + this.name + " and type of the assigned expression do not match.");
        }
        symTable.update(this.name, value);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        IType typeVar = typeEnv.getValue(this.name);
        IType typeExpr = this.expression.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr)) {
            return typeEnv;
        } else {
            throw new StatementExecutionException("Assignment: right hand side and left hand side have different types ");
        }
    }
}

