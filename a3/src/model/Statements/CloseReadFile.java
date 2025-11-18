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
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();

        IValue value = expression.eval(state.getSymTable());
        if (!value.getType().equals(new StringType())) {
            throw new StatementExecutionException(
                    "Expression " + expression + " is not of type string."
            );
        }

        String fileName = ((StringValue) value).getValue();
        if (!fileTable.isDefined(fileName)) {
            throw new StatementExecutionException(
                    "File " + fileName + " is not opened."
            );
        }

        BufferedReader reader = fileTable.getValue(fileName);
        try {
            reader.close();
            fileTable.remove(fileName);
        } catch (IOException e) {
            throw new StatementExecutionException(
                    "Could not close file " + fileName
            );
        }
        return state;
    }


    @Override
    public String toString() {
        return "closeRFile(" + this.expression.toString() + ")";
    }

}
