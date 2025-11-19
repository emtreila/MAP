package model.statements;

import model.ProgramState;

public class NopStatement implements IStatement {

    public NopStatement() {
    }

    @Override
    public String toString() {
        return "no operation";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }
}
