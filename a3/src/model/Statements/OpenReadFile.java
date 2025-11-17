package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Types.StringType;
import model.Values.IValue;
import model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFile implements IStatement {
    private IExpression expression;

    public OpenReadFile(IExpression exp) {
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IValue value = this.expression.eval(symTable);
        if (value.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) value;
            if (!fileTable.isDefined(fileName.getValue())){
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
                } catch (IOException e) {
                    throw new StatementExecutionException("Could not open file " + fileName.getValue());
                }
                fileTable.add(fileName.getValue(), bufferedReader);
            }
            else {
                throw new StatementExecutionException("File " + fileName.getValue() + " is already opened.");
            }
        } else {
            throw new StatementExecutionException("Expression " + this.expression + " is not of type string.");
        }
        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + expression.toString() + ")";
    }
}
