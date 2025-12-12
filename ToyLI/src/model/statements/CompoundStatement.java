package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.adts.IStack;
import model.ProgramState;
import model.types.IType;

public class CompoundStatement implements IStatement {
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + ";" + this.second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(this.second);
        exeStack.push(this.first);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        IDictionary<String, IType> typeEnv1 = this.first.typeCheck(typeEnv);
        IDictionary<String, IType> typeEnv2 = this.second.typeCheck(typeEnv1);
        return typeEnv2;
    }

}
