package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.adts.IHeap;
import model.values.IValue;

public interface IExpression {

    IValue eval(IDictionary<String, IValue> symTable, IHeap<Integer,IValue> heap) throws ExpressionEvaluationException;
}
