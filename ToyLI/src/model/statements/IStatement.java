package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IDictionary;
import model.types.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException;

    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException;
}
