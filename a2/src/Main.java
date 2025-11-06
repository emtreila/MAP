import controller.Controller;
import exceptions.StatementExecutionException;
import model.*;
import model.ADTs.*;
import model.Expressions.*;
import model.Statements.*;
import model.Types.*;
import model.Values.*;
import repository.*;
import view.View;

public class Main {
    public static void main(String[] args) {
        try {
            IStatement ex1 = new CompoundStatement(
                    new VarDeclStatement("v", new IntType()),
                    new CompoundStatement(
                            new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                            new PrintStatement(new VariableExpression("v"))
                    )
            );
            Repo repo1 = new Repo();
            repo1.addProgram(new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1));
            Controller ctrl1 = new Controller(repo1);

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
                                                            '*'),
                                                    '+')
                                    ),
                                    new CompoundStatement(
                                            new AssignmentStatement("b",
                                                    new ArithmeticExpression(
                                                            new VariableExpression("a"),
                                                            new ValueExpression(new IntValue(1)),
                                                            '+')
                                            ),
                                            new PrintStatement(new VariableExpression("b"))
                                    )
                            )
                    )
            );

            Repo repo2 = new Repo();
            repo2.addProgram(new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2));
            Controller ctrl2 = new Controller(repo2);

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
            Repo repo3 = new Repo();
            repo3.addProgram(new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3));
            Controller ctrl3 = new Controller(repo3);

            View view = new View();
            while (true) {
                int choice = view.run();
                if (choice == 0) {
                    System.out.println("Program terminated.");
                    break;
                }
                switch (choice) {
                    case 1 -> ctrl1.allStep();
                    case 2 -> ctrl2.allStep();
                    case 3 -> ctrl3.allStep();
                    default -> System.out.println("Invalid example number!");
                }
            }
        } catch (StatementExecutionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
