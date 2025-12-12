package repository;

import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Repo implements IRepo {
    private List<ProgramState> programStates;
    private String logFilePath;

    public Repo(ProgramState state, String logFilePath) {
        this.programStates = new ArrayList<>();
        this.logFilePath = logFilePath;
        this.programStates.add(state);
    }

    @Override
    public void logProgramStateExec(ProgramState programState) throws IOException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))) {
            logFile.println(programState.programStateToString());
        }
    }

    @Override
    public List<ProgramState> getProgramStatesList() {
        return this.programStates;
    }

    @Override
    public void setProgramStatesList(List<ProgramState> newProgramStates) {
        this.programStates = newProgramStates;
    }
}
