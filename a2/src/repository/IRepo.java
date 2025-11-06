package repository;

import model.ProgramState;

import java.util.List;

public interface IRepo {
    ProgramState getCurrentProgram();
    List<ProgramState> getProgramList();
    void addProgram(ProgramState programState);
    //void logPrgStateExec();
}
