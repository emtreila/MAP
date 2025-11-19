package model.expressions;

import exceptions.ExpressionEvaluationException;
import model.adts.IDictionary;
import model.values.IValue;

public interface IExpression {

    IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException;
}
