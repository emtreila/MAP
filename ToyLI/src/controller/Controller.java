package controller;

import model.ProgramState;
import model.values.IValue;
import model.values.RefValue;
import repository.IRepo;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private boolean displayFlag = true;
    private ExecutorService executor;


    public Controller(IRepo repo) {
        this.repo = repo;
    }


    public List<Integer> getReachableAddresses(Collection<IValue> startingValues, Map<Integer, IValue> heap) {

        Set<Integer> visitedAddresses = new HashSet<>();

        for (IValue v : startingValues) {
            if (v instanceof RefValue) {
                int addr = ((RefValue) v).getAddress();
                visitedAddresses.add(addr);
            }
        }

        boolean updated = true;
        while (updated) {
            updated = false;
            Set<Integer> copyOfVisited = new HashSet<>(visitedAddresses);
            for (Integer currAddr : copyOfVisited) {
                IValue val = heap.get(currAddr);
                if (val instanceof RefValue) {
                    int next = ((RefValue) val).getAddress();
                    if (!visitedAddresses.contains(next)) {
                        visitedAddresses.add(next);
                        updated = true;
                    }
                }
            }
        }
        return new ArrayList<>(visitedAddresses);
    }


    public Map<Integer, IValue> safeGarbageCollector(List<Integer> reachableAddresses, Map<Integer, IValue> heap) {

        Map<Integer, IValue> cleanedHeap = new HashMap<>();

        for (Map.Entry<Integer, IValue> heapEntry : heap.entrySet()) {
            Integer address = heapEntry.getKey();
            IValue storedValue = heapEntry.getValue();

            if (reachableAddresses.contains(address)) {
                cleanedHeap.put(address, storedValue);
            }
        }
        return cleanedHeap;
    }


    public void oneStepForAllPrg(List<ProgramState> prgList) throws InterruptedException {

        prgList.forEach(prg ->
        {
            try {
                this.repo.logProgramStateExec(prg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());

        List<ProgramState> newPrgList = this.executor.invokeAll(callList)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                this.repo.logProgramStateExec(prg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.repo.setProgramStatesList(prgList);
    }

    private void conservativeGarbageCollector(List<ProgramState> prgList) {

        List<IValue> allSymTableValues = new ArrayList<>();
        for (ProgramState prg : prgList) {
            for (String key : prg.getSymTable().getAllKeys()) {
                IValue value = prg.getSymTable().getValue(key);
                allSymTableValues.add(value);
            }
        }
        Map<Integer, IValue> heapContent = prgList.getFirst().getHeap().getContent();
        List<Integer> reachableFromSymTables = getReachableAddresses(allSymTableValues, heapContent);


        List<IValue> allHeapValues = new ArrayList<>();
        for (ProgramState prg : prgList) {
            for (IValue heapVal : prg.getHeap().getContent().values()) {
                allHeapValues.add(heapVal);
            }
        }
        List<Integer> reachableFromHeapValues = getReachableAddresses(allHeapValues, heapContent);


        List<Integer> allReachable = new ArrayList<>();
        for (Integer addr : reachableFromSymTables) {
            allReachable.add(addr);
        }
        for (Integer addr : reachableFromHeapValues) {
            allReachable.add(addr);
        }


        Map<Integer, IValue> cleanedHeap = safeGarbageCollector(allReachable, heapContent);
        for (ProgramState prg : prgList) {
            prg.getHeap().setContent(cleanedHeap);
        }
    }


    public void allStep() throws InterruptedException {
        this.executor = java.util.concurrent.Executors.newFixedThreadPool(2);

        List<ProgramState> prgList = removeCompletedProgramState(this.repo.getProgramStatesList());

        while (!prgList.isEmpty()) {

            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedProgramState(this.repo.getProgramStatesList());
        }

        executor.shutdownNow();
        this.repo.setProgramStatesList(prgList);
    }


    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    private void display(ProgramState program) {
        System.out.println(program);
    }

    public List<ProgramState> removeCompletedProgramState(List<ProgramState> inProgramStateList) {
        return inProgramStateList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }
}
    