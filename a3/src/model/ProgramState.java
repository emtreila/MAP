package model;

import model.adts.*;
import model.statements.IStatement;
import model.values.IValue;

import java.io.BufferedReader;
import java.util.List;


public class ProgramState {

    private IStack<IStatement> exeStack; // = stack of statements to execute the current program
    private IDictionary<String, IValue> symTable; // = table which keeps the variables values
    private IList<IValue> output; // = list of output messages printed by the ToyLI
    private IDictionary<String, BufferedReader> fileTable;

    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> symTable, IList<IValue> output, IDictionary<String, BufferedReader> fileTable, IStatement program) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.output = output;
        this.exeStack.push(program);
        this.fileTable = fileTable;
    }

    public IStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public IDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public IList<IValue> getOutput() {
        return this.output;
    }

    public IDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setExeStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public void setOutput(IList<IValue> output) {
        this.output = output;
    }

    public void setFileTable(IDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }


    @Override
    public String toString() {
        return "ExeStack: " + this.exeStack + "\n" +
                "SymTable: " + this.symTable + "\n" +
                "Output: " + this.output + "\n" +
                "FileTable: " + this.fileTable + "\n";
    }

    public String programStateToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExeStack:\n");
        List<IStatement> stack = this.exeStack.getReversedList();
        for (IStatement stmt : stack) {
            sb.append("   ").append(stmt.toString()).append("\n");
        }

        sb.append("SymTable:\n");
        for (String key : this.symTable.getAllKeys()) {
            try {
                sb.append("   ").append(key)
                        .append(" --> ")
                        .append(this.symTable.getValue(key))
                        .append("\n");
            } catch (Exception e) {
                sb.append("   ").append(key)
                        .append(" --> [error reading value]\n");
            }
        }

        sb.append("Out:\n");
        for (IValue val : this.output.getAll()) {
            sb.append("   ").append(val.toString()).append("\n");
        }

        sb.append("FileTable:\n");
        for (String key : this.fileTable.getAllKeys()) {
            sb.append("   ").append(key).append("\n");
        }
        return sb.toString();
    }


}
    
    
