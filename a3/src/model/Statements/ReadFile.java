package model.Statements;

import exceptions.StatementExecutionException;
import model.ADTs.IDictionary;
import model.Expressions.IExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.StringType;
import model.Values.IValue;
import model.Values.IntValue;
import model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression expression;
    private String varName;

    public ReadFile(IExpression expr, String varName) {
        this.expression = expr;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (!symTable.isDefined(this.varName)) {
            throw new StatementExecutionException("Variable " + this.varName + " is not defined.");
        }

        IValue varValue = symTable.getValue(this.varName);
        if (!varValue.getType().equals(new IntType())) {
            throw new StatementExecutionException("Variable " + this.varName + " is not of type int.");
        }

        IValue exprValue = this.expression.eval(symTable);
        if (!exprValue.getType().equals(new StringType())) {
            throw new StatementExecutionException("Expression " + this.expression + " is not of type string.");
        }

        String fileName = ((StringValue) exprValue).getValue();
        if (!fileTable.isDefined(fileName)) {
            throw new StatementExecutionException("The File Table does not contain " + fileName);
        }

        BufferedReader reader = fileTable.getValue(fileName);
        try {
            String line = reader.readLine();

            IValue newValue;
            if (line == null) {
                newValue = varValue.getType().defaultValue();
            } else {
                newValue = new IntValue(Integer.parseInt(line));
            }
            symTable.update(varName, newValue);

        } catch (IOException e) {
            throw new StatementExecutionException("Could not read from file " + fileName);
        } catch (NumberFormatException e) {
            throw new StatementExecutionException("File " + fileName + " contains a non-integer value.");
        }
        return state;
    }


    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.varName + ")";
    }
}
