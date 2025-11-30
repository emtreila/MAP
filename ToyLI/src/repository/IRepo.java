package repository;

import model.ProgramState;

import java.io.IOException;


public interface IRepo {
    ProgramState getCurrentProgram();

    void logProgramStateExec(ProgramState programState) throws IOException;
}
