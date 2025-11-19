package model.statements;


import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.adts.IList;
import model.expressions.IExpression;
import model.ProgramState;
import model.values.IValue;

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
        IList<IValue> out = state.getOutput();
        IDictionary<String, IValue> symTable = state.getSymTable();

        IValue value = this.exp.eval(symTable);
        out.add(value);

        return state;
    }
}
