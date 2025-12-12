package repository;

import model.ProgramState;

import java.io.IOException;
import java.util.List;


public interface IRepo {

    void logProgramStateExec(ProgramState programState) throws IOException;
    
    List<ProgramState> getProgramStatesList();
    
    void setProgramStatesList(List<ProgramState> programStates);
}
