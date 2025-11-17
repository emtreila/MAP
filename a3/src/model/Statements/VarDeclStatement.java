package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.ProgramState;
import model.Types.IType;
import model.Values.IValue;

public class VarDeclStatement implements IStatement {
    
    private String name;
    private IType type;

    public VarDeclStatement(String name, IType type){
        this.name = name;
        this.type = type;
    }
    
    @Override
    public String toString(){
        return this.type + " " + this.name;
    }
    
    @Override
    public ProgramState execute (ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        
        if (symTable.isDefined(name)) {
            throw new StatementExecutionException("Variable already defined!");
        }
        symTable.add(this.name, this.type.defaultValue());
        return state;
    }
}
