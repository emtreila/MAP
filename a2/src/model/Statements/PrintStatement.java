package model.Statements;


import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.ADTs.IList;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Values.IValue;

public class PrintStatement implements IStatement {
    private IExpression exp;

    public PrintStatement(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + this.exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IList<IValue> out = state.getOut();
        IDictionary<String, IValue> symTable = state.getSymTable();

        IValue value = this.exp.eval(symTable);
        out.add(value);

        return state;
    }
}
