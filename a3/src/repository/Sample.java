package repository;

import model.expressions.AOperator;
import model.expressions.ArithmeticExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

public class Sample {
    private IStatement[] statement;

    public Sample() {
        this.statement = new IStatement[5];
        this.addStatements();
        ;
    }

    public void addStatements() {
        IStatement ex1 = new CompoundStatement(
                new VarDeclStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        IStatement ex2 = new CompoundStatement(
                new VarDeclStatement("a", new IntType()),
                new CompoundStatement(
                        new VarDeclStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a",
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5)),
                                                        AOperator.MUL),
                                                AOperator.ADD)
                                ),
                                new CompoundStatement(
                                        new AssignmentStatement("b",
                                                new ArithmeticExpression(
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)),
                                                        AOperator.ADD)
                                        ),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );

        IStatement ex3 = new CompoundStatement(
                new VarDeclStatement("a", new BoolType()),
                new CompoundStatement(
                        new VarDeclStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );

        IStatement ex4 = new CompoundStatement(
                new VarDeclStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VarDeclStatement("varc", new IntType()),
                                        new CompoundStatement(
                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        this.statement[0] = ex1;
        this.statement[1] = ex2;
        this.statement[2] = ex3;
        this.statement[3] = ex4;
    }

    public IStatement getStatement(int index) {
        return this.statement[index];
    }
}
