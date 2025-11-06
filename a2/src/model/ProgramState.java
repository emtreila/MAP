package model;

import model.ADTs.*;
import model.Statements.IStatement;
import model.Values.IValue;


public class ProgramState {

    private IStack<IStatement> exeStack; // = stack of statements to execute the current program
    private IDictionary<String, IValue> symTable; // = table which keeps the variables values
    private IList<IValue> out; // = list of output messages printed by the ToyLI

    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> symTable, IList<IValue> output, IStatement program) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.out = output;
        this.exeStack.push(program);
    }
    
    public IStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public IDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public IList<IValue> getOut() {
        return this.out;
    }

    public void setExeStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "ExeStack: " + this.exeStack.toString() + "\n" +
                "SymTable: " + this.symTable.toString() + "\n" +
                "Out: " + this.out.toString() + "\n";
    }

}
    
    
