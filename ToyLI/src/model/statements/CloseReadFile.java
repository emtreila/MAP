package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.expressions.IExpression;
import model.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

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

        IValue value = this.expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new StatementExecutionException(
                    "Expression " + this.expression + " is not of type string."
            );
        }

        String fileName = ((StringValue) value).getValue();
        if (!fileTable.isDefined(fileName)) {
            throw new StatementExecutionException(
                    "File " + fileName + " is not defined in the file table."
            );
        }

        BufferedReader reader = fileTable.getValue(fileName);
        try {
            reader.close();
            fileTable.remove(fileName);
        } catch (IOException e) {
            throw new StatementExecutionException("Could not close file " + fileName);
        }
        return state;
    }


    @Override
    public String toString() {
        return "closeRFile(" + this.expression.toString() + ")";
    }

}
