package view;

import controller.Controller;

import java.io.IOException;

public class RunExample extends Command {

    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            this.controller.allStep();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
