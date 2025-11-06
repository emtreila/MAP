package model.Expressions;

import exceptions.ExpressionEvaluationException;
import model.ADTs.IDictionary;
import model.Values.IValue;

public interface IExpression {

    IValue eval(IDictionary<String, IValue> symTable) throws ExpressionEvaluationException;
}
