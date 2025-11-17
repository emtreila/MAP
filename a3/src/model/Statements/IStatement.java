package model.Statements;

import exceptions.StatementExecutionException;
import model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException;
}
