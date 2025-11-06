package repository;

import model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    private List<ProgramState> programStates;
    
    public Repo() {
        this.programStates = new ArrayList<>();
    }
    
    @Override
    public ProgramState getCurrentProgram() {
        if (this.programStates.isEmpty()) {
            throw new RuntimeException();
        }
        return this.programStates.getFirst();
    }
    
    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }
    
    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }
    
            
}
