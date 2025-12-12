package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.ProgramState;
import model.types.IType;
import model.values.IValue;

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
        return null;
    }
    
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException{
        typeEnv.add(this.name, this.type);
        return typeEnv;
    }
}
