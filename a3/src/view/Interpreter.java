package view;

import controller.Controller;
import exceptions.InterpreterException;
import model.*;
import model.ADTs.*;
import model.Expressions.*;
import model.Statements.*;
import model.Types.*;
import model.Values.*;
import repository.*;

public class Interpreter {
    public static void main(String[] args) {

        Sample samples = new Sample();
        IStatement ex1 = samples.getStatement(0);
        IStatement ex2 = samples.getStatement(1);
        IStatement ex3 = samples.getStatement(2);
        IStatement ex4 = samples.getStatement(3);
        
        ProgramState prg1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex1);
        ProgramState prg2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex2);
        ProgramState prg3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex3);
        ProgramState prg4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex4);

        Repo repo1 = new Repo(prg1, "log1.txt");
        Repo repo2 = new Repo(prg2, "log2.txt");
        Repo repo3 = new Repo(prg3, "log3.txt");
        Repo repo4 = new Repo(prg4, "log4.txt");

        Controller ctrl1 = new Controller(repo1);
        Controller ctrl2 = new Controller(repo2);
        Controller ctrl3 = new Controller(repo3);
        Controller ctrl4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));

        menu.show();

        
    }
}
