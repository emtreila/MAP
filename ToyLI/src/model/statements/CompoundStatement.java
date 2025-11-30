package model.statements;

import model.adts.IStack;
import model.ProgramState;

public class CompoundStatement implements IStatement {
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString(){
        return "(" + this.first.toString() + ";" + this.second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state){
        IStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(this.second);
        exeStack.push(this.first);
        return state;
    }
}
