package model.statements;


import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.adts.IList;
import model.expressions.IExpression;
import model.ProgramState;
import model.types.IType;
import model.values.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression exp) {
        this.expression = exp;
    }

    @Override
    public String toString() {
        return "print(" + this.expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IList<IValue> out = state.getOutput();
        IDictionary<String, IValue> symTable = state.getSymTable();

        IValue value = this.expression.eval(symTable, state.getHeap());
        out.add(value);

        return null;
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }

}
