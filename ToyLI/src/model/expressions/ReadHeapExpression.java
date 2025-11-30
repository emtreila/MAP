package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.values.IValue;
import model.values.RefValue;

public class ReadHeapExpression implements IExpression {
    private IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap<Integer, IValue> heap) throws ExpressionEvaluationException {
        IValue value = this.expression.eval(symTable, heap);

        if (!(value instanceof RefValue)) {
            throw new ExpressionEvaluationException("The evaluated expression is not a reference value.");
        }

        RefValue refValue = (RefValue) value;
        if (!heap.isDefined(refValue.getAddress())) {
            throw new ExpressionEvaluationException("The address " + refValue.getAddress() + " is not defined in the heap.");
        }
        
        return heap.getValue(refValue.getAddress());
    }
    
    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }
}
