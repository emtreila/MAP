package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.*;
import model.types.IType;
import model.values.IValue;

import java.io.BufferedReader;


public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> newSymTable = state.getSymTable().deepCopy();
        IStack<IStatement> newStack = new MyStack<>();
        IList<IValue> output = state.getOutput();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<Integer, IValue> heap = state.getHeap();

        return new ProgramState(newStack, newSymTable, output, fileTable, heap, this.statement);

    }

    @Override
    public String toString() {
        return "fork(" + this.statement + ")";
    }
    
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        this.statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
