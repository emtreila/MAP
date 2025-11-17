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

        if (symTable.isDefined(this.varName)) {
            IValue variableValue = symTable.getValue(this.varName);
            if (variableValue.getType().equals(new IntType())) {
                IValue value = this.expression.eval(symTable);
                if (value.getType().equals(new StringType())) {
                    StringValue castedFileNameValue = (StringValue) value;
                    if (fileTable.isDefined(castedFileNameValue.getValue())) {
                        BufferedReader bufferedReader = fileTable.getValue(castedFileNameValue.getValue());
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                line = "0";
                            }
                            symTable.update(this.varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementExecutionException("Could not read from file " + castedFileNameValue.getValue());
                        } catch (NumberFormatException e) {
                            throw new StatementExecutionException("File " + castedFileNameValue.getValue() + " contains a non-integer value.");
                        }
                    } else {
                        throw new StatementExecutionException("The File Table does not contain " + castedFileNameValue.getValue());
                    }
                } else {
                    throw new StatementExecutionException("Expression " + this.expression.toString() + " is not of type string.");
                }
            } else {
                throw new StatementExecutionException("Variable " + this.varName + " is not of type int.");
            }
        } else {
            throw new StatementExecutionException("Variable " + this.varName + " is not defined.");
        }
        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + this.expression.toString() + ", " + this.varName + ")";
    }
}
