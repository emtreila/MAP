package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Types.StringType;
import model.Values.IValue;
import model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements IStatement {
    private IExpression expression;

    public CloseReadFile(IExpression expression) {
        this.expression = expression;
    }
    
    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IValue value = this.expression.eval(state.getSymTable());
        if (value.getType().equals(new StringType())){
            StringValue fileName = (StringValue) value;
            if (fileTable.isDefined(fileName.getValue())){
                BufferedReader bufferedReader = fileTable.getValue(fileName.getValue());
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new StatementExecutionException("Could not close file " + fileName.getValue());
                }
                fileTable.remove(fileName.getValue());
            }
            else {
                throw new StatementExecutionException("File " + fileName.getValue() + " is not opened.");
            }
        }
        else {
            throw new StatementExecutionException("Expression " + this.expression + " is not of type string.");
        }
        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + this.expression.toString() + ")";
    }

}
