package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Types.IType;
import model.Values.IValue;


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

        if (symTable.isDefined(this.name)) {
            IValue value = this.expression.eval(symTable);
            IType typeName = (symTable.getValue(this.name)).getType();
            if (value.getType().equals(typeName)) {
                symTable.update(this.name, value);
            } else {
                throw new StatementExecutionException("Declared type of variable " + this.name + " and type of the assigned expression do not match.");
            }
        } else {
            throw new StatementExecutionException("The used variable " + this.name + " was not declared before.");
        }
        return state;
    }
}

