package model.statements;

import exceptions.StatementExecutionException;
import model.ProgramState;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.expressions.IExpression;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class NewStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public NewStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();

        if (!symTable.isDefined(this.varName)) {
            throw new StatementExecutionException("Variable " + this.varName + " is not defined.");
        }

        IValue varValue = symTable.getValue(this.varName);
        if (!(varValue.getType() instanceof RefType)) {
            throw new StatementExecutionException("Variable " + this.varName + " is not of type RefType.");
        }

        RefType varRefType = (RefType) varValue.getType();
        IValue exprValue = this.expression.eval(symTable, heap);
        if (!exprValue.getType().equals(varRefType.getInner())) {
            throw new StatementExecutionException("Variable " + this.varName + " expects " + varRefType.getInner() +
                    " but expression is " + exprValue.getType());
        }

        Integer newAddress = heap.add(exprValue);
        IValue newRefValue = new RefValue(newAddress, varRefType.getInner());
        symTable.update(this.varName, newRefValue);
        return null;
    }

    @Override
    public String toString() {
        return "new(" + this.varName + ", " + this.expression.toString() + ")";
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws StatementExecutionException {
        IType typeVar = typeEnv.getValue(this.varName);
        IType typeExpr = this.expression.typeCheck(typeEnv);
        if (!typeVar.equals(new RefType(typeExpr))) {
            throw new StatementExecutionException("NEW stmt: right hand side and left hand side have different types ");
        }
        return typeEnv;
    }
}
