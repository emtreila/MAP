package controller;

import exceptions.StatementExecutionException;
import model.adts.IStack;
import model.ProgramState;
import model.statements.IStatement;
import model.values.IValue;
import model.values.RefValue;
import repository.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo repo;
    private boolean displayFlag = true;


    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public List<Integer> getAddressesFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    private Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // ! unsafe because it only considers addresses from the symbol table, not from the heap itself
    }

    public List<Integer> getAddressesFromValues(Collection<IValue> values) {
        return values.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return getAddressesFromValues(symTableValues);
    }

    public List<Integer> getAddrFromHeap(Collection<IValue> heapValues) {
        return getAddressesFromValues(heapValues);
    }

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symTableAddr,
                                                     List<Integer> heapAddr,
                                                     Map<Integer, IValue> heap) {
        List<Integer> validAddresses =
                Stream.concat(symTableAddr.stream(), heapAddr.stream())
                        .distinct()
                        .collect(Collectors.toList());

        return heap.entrySet().stream()
                .filter(e -> validAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // ! safe because it considers addresses from both the symbol table and the heap
    }

    public ProgramState oneStep(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExeStack();
        if (exeStack.isEmpty()) {
            throw new StatementExecutionException("Program state stack is empty!");
        }
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws StatementExecutionException, IOException {
        ProgramState state = this.repo.getCurrentProgram();
        this.repo.logProgramStateExec(state);
        while (!state.getExeStack().isEmpty()) {
            oneStep(state);
            this.repo.logProgramStateExec(state);

//            // UNSAFE VERSION
//            program.getHeap().setContent(unsafeGarbageCollector(getAddressesFromSymTable(
//                            program.getSymTable().getContent().values()),
//                    program.getHeap().getContent()));
//            this.repo.logProgramStateExec(program);
//            // UNSAFE VERSION

            // SAFE VERSION
            List<IValue> symTableValues = state.getSymTable()
                    .getAllKeys()
                    .stream()
                    .map(key -> state.getSymTable().getValue(key))
                    .collect(Collectors.toList());

            Collection<IValue> heapValues = state.getHeap().getContent().values();

            List<Integer> symTableAddr = getAddrFromSymTable(symTableValues);
            List<Integer> heapAddr = getAddrFromHeap(heapValues);

            Map<Integer, IValue> newHeapContent =
                    safeGarbageCollector(symTableAddr, heapAddr, state.getHeap().getContent());

            state.getHeap().setContent(newHeapContent);
            this.repo.logProgramStateExec(state);
            // SAFE VERSION
            
            display(state);
        }
    }

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    private void display(ProgramState program) {
        System.out.println(program);
    }
}
    
