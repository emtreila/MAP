package controller;

import exceptions.StatementExecutionException;
import model.ADTs.IStack;
import model.ProgramState;
import model.Statements.IStatement;
import repository.IRepo;

public class Controller {
    private IRepo repo;
    private boolean displayFlag = true;


    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public ProgramState oneStep(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExeStack();
        if (exeStack.isEmpty()) {
            throw new StatementExecutionException("Program state stack is empty!");
        }
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws StatementExecutionException {
        ProgramState program = repo.getCurrentProgram();
        while (!program.getExeStack().isEmpty()) {
            oneStep(program);
            display(program);
        }
    }

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    private void display(ProgramState program) {
        System.out.println(program.toString());
    }
}
    
