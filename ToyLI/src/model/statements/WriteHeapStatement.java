package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.expressions.IExpression;
import model.values.IValue;
import model.values.RefValue;

public class WriteHeapStatement implements IStatement {
    private IExpression expression;
    private String varName;

    public WriteHeapStatement(IExpression expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();

        if (!symTable.isDefined(this.varName)) {
            throw new StatementExecutionException("Variable " + this.varName + " is not defined.");
        }

        IValue varValue = symTable.getValue(this.varName);
        if (!(varValue instanceof RefValue)) {
            throw new StatementExecutionException("Variable " + this.varName + " is not of RefType.");
        }

        RefValue refValue = (RefValue) varValue;
        int address = refValue.getAddress();
        if (!heap.isDefined(address)) {
            throw new StatementExecutionException("Address " + address + " is not allocated in the heap.");
        }

        IValue exprValue = this.expression.eval(symTable, heap);
        if (!exprValue.getType().equals(refValue.getLocationType())) {
            throw new StatementExecutionException(
                    "Type mismatch: variable '" + this.varName + "' expects " +
                            refValue.getLocationType() + " but expression evaluated to " +
                            exprValue.getType()
            );
        }

        heap.update(address, exprValue);

        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.varName + ", " + this.expression.toString() + ")";
    }

}

