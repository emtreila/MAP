package model;

import exceptions.StackException;
import model.adts.*;
import model.statements.IStatement;
import model.values.IValue;

import java.io.BufferedReader;
import java.util.List;


public class ProgramState {

    private int id;
    private static int lastId = 0;
    private IStack<IStatement> exeStack; // = stack of statements to execute the current program
    private IDictionary<String, IValue> symTable; // = table which keeps the variables values
    private IList<IValue> output; // = list of output messages printed by the ToyLI
    private IDictionary<String, BufferedReader> fileTable;
    private IHeap<Integer, IValue> heap;

    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> symTable, IList<IValue> output,
                        IDictionary<String, BufferedReader> fileTable, IHeap<Integer, IValue> heap, IStatement program) {
        this.exeStack = stack;
        this.symTable = symTable;
        this.output = output;
        this.exeStack.push(program);
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = generateNewId();
    }
    

    public static synchronized int generateNewId() {
        return ++lastId;
    }

    public IStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public void setExeStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }


    public IDictionary<String, IValue> getSymTable() {
        return this.symTable;
    }

    public void setSymTable(IDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public IList<IValue> getOutput() {
        return this.output;
    }

    public void setOutput(IList<IValue> output) {
        this.output = output;
    }

    public IDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setFileTable(IDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public IHeap<Integer, IValue> getHeap() {
        return heap;
    }

    public void setHeap(IHeap<Integer, IValue> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\n" +
                "ExeStack: " + this.exeStack + "\n" +
                "SymTable: " + this.symTable + "\n" +
                "Output: " + this.output + "\n" +
                "FileTable: " + this.fileTable + "\n" +
                "Heap: " + this.heap + "\n";
    }

    public String programStateToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id).append("\n");
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

        sb.append("Heap:\n");
        for (Integer address : this.heap.getAllKeys()) {
            try {
                sb.append("   ").append(address)
                        .append(" --> ")
                        .append(this.heap.getValue(address))
                        .append("\n");
            } catch (Exception e) {
                sb.append("   ").append(address)
                        .append(" --> [error reading heap value]\n");
            }
        }
        return sb.toString();
    }

    public Boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public ProgramState oneStep() throws StackException {
        if (this.exeStack.isEmpty()) {
            throw new StackException("Program state stack is empty!");
        }
        IStatement currentStatement = this.exeStack.pop();
        return currentStatement.execute(this);
    }
}
    
    
