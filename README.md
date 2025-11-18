# MAP – Advanced Programming Methods
### Toy Language Interpreter (Java)

This repository contains the full set of **Advanced Programming Methods (MAP)** assignments developed during my 2nd year at **Universitatea Babeș–Bolyai**.  
The project evolves step-by-step across the semester into a complete **toy language interpreter**, covering all features required in labs, homework, and exam preparation.

---

##  Core Features

### Expression Evaluation
- Arithmetic expressions  
- Boolean and relational expressions  
- Logical expressions  
- Heap read operations (`rH`)

### Statement Execution
- Variable declarations  
- Assignments  
- Print statements  
- Compound statements  
- Conditionals (`if`)  
- Loops (`while`)  
- File operations: `openRFile`, `readFile`, `closeRFile`  
- Heap operations: allocation (`new`), write (`wH`)

### Program State Components
Each program state includes:
- Execution stack  
- Symbol table  
- Output list  
- File table  
- Heap  

### Repository & Logging
All execution steps are logged to an external file for debugging and semantic inspection.

### Garbage Collector
A conservative garbage collector removes unreachable heap addresses based on references reachable from the symbol table.

---


