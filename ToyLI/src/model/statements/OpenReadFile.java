package model.statements;

import exceptions.StatementExecutionException;
import model.adts.IDictionary;
import model.expressions.IExpression;
import model.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

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

        IValue value = this.expression.eval(symTable, state.getHeap());
        if (!value.getType().equals(new StringType())) {
            throw new StatementExecutionException(
                    "Expression " + this.expression + " is not of type string."
            );
        }

        String fileName = ((StringValue) value).getValue();
        if (fileTable.isDefined(fileName)) {
            throw new StatementExecutionException(
                    "File " + fileName + " is already opened."
            );
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            fileTable.add(fileName, reader);
        } catch (IOException e) {
            throw new StatementExecutionException(
                    "Could not open file " + fileName
            );
        }
        return state;
    }


    @Override
    public String toString() {
        return "openRFile(" + expression.toString() + ")";
    }
}
