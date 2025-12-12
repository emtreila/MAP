package repository;

import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;

import java.util.ArrayList;

public class Sample {
    private ArrayList<IStatement> statement;

    public Sample() {
        this.statement = new ArrayList<>();
        this.addStatements();
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

        IStatement ex5 = new CompoundStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );

        IStatement ex6 = new CompoundStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new VariableExpression("v")
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new ArithmeticExpression(
                                                                new ReadHeapExpression(
                                                                        new ReadHeapExpression(
                                                                                new VariableExpression("a")
                                                                        )
                                                                ),
                                                                new ValueExpression(new IntValue(5)),
                                                                AOperator.ADD
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement ex7 = new CompoundStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(
                                                new VariableExpression("v")
                                        )
                                ),
                                new CompoundStatement(
                                        new WriteHeapStatement(
                                                "v", new ValueExpression(new IntValue(30))
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        new ReadHeapExpression(
                                                                new VariableExpression("v")
                                                        ),
                                                        new ValueExpression(new IntValue(5)),
                                                        AOperator.ADD
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement ex8 = new CompoundStatement(
                new VarDeclStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement ex9 = new CompoundStatement(
                new VarDeclStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0)),
                                                ROperator.GREATER
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntValue(1)),
                                                                AOperator.SUB
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );


//        IStatement ex10 = new CompoundStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(
//                                                new VarDeclStatement("b", new RefType(
//                                                        new RefType(new RefType(new IntType())))
//                                                ),
//                                                new CompoundStatement(
//                                                        new NewStatement("b", new VariableExpression("a")),
//                                                        new CompoundStatement(
//                                                                new NewStatement("v",
//                                                                        new ValueExpression(new IntValue(30))),
//                                                                new CompoundStatement(
//                                                                        new WriteHeapStatement(
//                                                                                new VariableExpression("v"),
//                                                                                "a"
//                                                                        ),
//                                                                        new PrintStatement(
//                                                                                new ReadHeapExpression(
//                                                                                        new ReadHeapExpression(
//                                                                                                new ReadHeapExpression(
//                                                                                                        new VariableExpression("b")
//                                                                                                )
//                                                                                        )
//                                                                                )
//                                                                        )
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );

//        IStatement ex10 = new CompoundStatement(
//                new VarDeclStatement("v", new RefType(new IntType())),
//                new CompoundStatement(
//                        new NewStatement("v", new ValueExpression(new IntValue(20))),
//                        new CompoundStatement(
//                                new VarDeclStatement("a", new RefType(new RefType(new IntType()))),
//                                new CompoundStatement(
//                                        new NewStatement("a", new VariableExpression("v")),
//                                        new CompoundStatement(
//                                                new VarDeclStatement("b",
//                                                        new RefType(new RefType(new RefType(new IntType())))),
//                                                new CompoundStatement(
//                                                        new NewStatement("b", new VariableExpression("a")),
//                                                        new CompoundStatement(
//                                                                new VarDeclStatement("w",
//                                                                        new RefType(new IntType())),
//                                                                new CompoundStatement(
//                                                                        new NewStatement("w",
//                                                                                new ValueExpression(new IntValue(30))),
//                                                                        new CompoundStatement(
//                                                                                new NewStatement("v",
//                                                                                        new ValueExpression(new IntValue(40))),
//                                                                                new CompoundStatement(
//                                                                                        new NewStatement("a",
//                                                                                                new VariableExpression("w")),
//                                                                                        new PrintStatement(
//                                                                                                new ReadHeapExpression(
//                                                                                                        new ReadHeapExpression(
//                                                                                                                new ReadHeapExpression(
//                                                                                                                        new VariableExpression("b")
//                                                                                                                )
//                                                                                                        )
//                                                                                                )
//                                                                                        )
//                                                                                )
//                                                                        )
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );

        IStatement ex10 = new CompoundStatement(
                new VarDeclStatement("v", new IntType()),
                new CompoundStatement(
                        new VarDeclStatement("a", new RefType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement("a",
                                                                        new ValueExpression(new IntValue(30))

                                                                ),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v",
                                                                                new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression("v")),
                                                                                new PrintStatement(
                                                                                        new ReadHeapExpression(
                                                                                                new VariableExpression("a")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );


        this.statement.add(ex1);
        this.statement.add(ex2);
        this.statement.add(ex3);
        this.statement.add(ex4);
        this.statement.add(ex5);
        this.statement.add(ex6);
        this.statement.add(ex7);
        this.statement.add(ex8);
        this.statement.add(ex9);
        this.statement.add(ex10);
    }

    public IStatement getStatement(int index) {
        return this.statement.get(index);
    }

    public int getSize() {
        return this.statement.size();
    }
}
