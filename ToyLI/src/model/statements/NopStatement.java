package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IDictionary;
import model.types.IType;

public class NopStatement implements IStatement {

    public NopStatement() {
    }

    @Override
    public String toString() {
        return "no operation";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }
    
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        return typeEnv;
    }
}
