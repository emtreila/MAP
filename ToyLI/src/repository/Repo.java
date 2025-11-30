package repository;

import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Repo implements IRepo {
    private ProgramState programStates;
    private String logFilePath;

    public Repo(ProgramState state, String logFilePath) {
        this.programStates = state;
        this.logFilePath = logFilePath;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return this.programStates;
    }

    @Override
    public void logProgramStateExec(ProgramState programState) throws IOException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))) {
            logFile.println(programState.programStateToString());
        }
    }


}
