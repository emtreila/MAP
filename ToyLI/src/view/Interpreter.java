package view;

import controller.Controller;
import model.*;
import model.adts.*;
import model.statements.*;
import repository.*;

public class Interpreter {
    public static Controller makeController(IStatement statement, String logFile) {
        ProgramState state = new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                statement
        );
        Repo repo = new Repo(state, logFile);
        return new Controller(repo);
    }


    public static void main(String[] args) {
        Sample samples = new Sample();
        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "Exit"));
        for (int i = 0; i < samples.getSize(); i++) {
            IStatement statement = samples.getStatement(i);
            Controller controller = makeController(statement, "log" + (i + 1) + ".txt");
            menu.addCommand(
                    new RunExample(
                            String.valueOf(i + 1),
                            statement.toString(),
                            controller
                    )
            );
        }
        menu.show();
    }
}
