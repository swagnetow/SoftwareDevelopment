package interpreter.debugger;

import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.HaltCode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;

public class DebugVM extends VirtualMachine {
    private ArrayDeque<FunctionEnvironmentRecord> environmentStack;
    private ArrayList<BreakPoints> sourceLines;
    private boolean isQuit;
    private int envSize;
    private String step;
    private boolean traceStatus;
    private String intrinsicFunctionName;

    public DebugVM(Program program, ArrayList<BreakPoints> sourceLines) {
        super(program);
        this.environmentStack = new ArrayDeque<FunctionEnvironmentRecord>();
        this.sourceLines = sourceLines;
        this.isRunning = true;
        this.pc = 0;
        environmentStack.push(new FunctionEnvironmentRecord());
        this.envSize = environmentStack.size();
        this.step = new String();
        this.traceStatus = false;
        this.intrinsicFunctionName = new String();
    }

    @Override
    public void executeProgram() {
        // Get the environment stack size before it is changed.
        envSize = environmentStack.size();

        while(isRunning) {
            ByteCode code = program.getCode(pc);
            String currentByteCodeName = program.getCode(pc).getClass().getSimpleName();
            int currentLine = getCurrentLine();

            code.execute(this);

            // Goes into a step function to carry out the the command when called.
            if((step.equals("out") || step.equals("into") || step.equals("over")) && !currentByteCodeName.equals("DebugCallCode")) {
                stepFunction();
            }

            // Prevents the execute from breaking when trying to call a getCode when the program
            // counter goes beyond the program.
            if(!(code instanceof HaltCode)) {
                // Check if a FunctionCode is executed after a LineCode
                if(getNextByteCodeName().equals("FunctionCode")) {
                    pc++;
                    code = program.getCode(pc);
                    code.execute(this);

                    // When the the bytecode is "LINE -1", set the name of the intrinsic function.
                    if(getStartLine() < 0 || getEndLine() < 0) {
                        intrinsicFunctionName = getFunctionName();
                    }

                    // This is to correctly display the variables when current line is on a function.
                    if(getNextByteCodeName().equals("FormalCode")) {
                        pc++;
                        code = program.getCode(pc);
                        code.execute(this);
                    }
                }
            }
            // If a HaltCode is encountered, stop execution of the program.
            else {
                isQuit = true;
            }

            pc++;
        }
    }

    public String getCurrentByteCodeName() {
        return program.getCode(pc).getClass().getSimpleName();
    }

    public String getNextByteCodeName() {
        return program.getCode(pc+1).getClass().getSimpleName();
    }

    public void setStep(String name) {
        this.step = name;
    }

    // Sets isRunning to false and stops at a line if the condition is satisfied.
    public void stepFunction() {
        if(step.equals("out") && (envSize > currentEnvSize() && getCurrentLine() > 0)) {
            isRunning = false;
        }
        else if(step.equals("into") && ((getCurrentByteCodeName().equals("LineCode") && envSize == currentEnvSize()) || getCurrentByteCodeName().equals("DebugReturnCode") || envSize < currentEnvSize() || (getCurrentByteCodeName().equals("LineCode") && getNextByteCodeName().equals("FunctionCode")) && (getStartLine() != 0 || getEndLine() != 0 || getCurrentLine() != 0))) {
            isRunning = false;
        }
        else if(step.equals("over") && ((getCurrentByteCodeName().equals("LineCode") && envSize == currentEnvSize()) || getCurrentByteCodeName().equals("DebugReturnCode") || envSize > currentEnvSize())) {
            isRunning = false;
        }
    }

    public void addFunction() {
        FunctionEnvironmentRecord fer = new FunctionEnvironmentRecord();
        environmentStack.push(fer);
    }

    public void setFunction(String name, int start, int end) {
        environmentStack.peek().setFunctionName(name);
        environmentStack.peek().setStartLine(start);
        environmentStack.peek().setEndLine(end);
    }

    public void addRecord(String id, int offset) {
        environmentStack.peek().enter(id, offset);
    }

    public void popSymbol(int n) {
        environmentStack.peek().table.endScope(n);
    }

    public void popRecord() {
        environmentStack.pop();
    }

    public String getSourceLine(int line) {
        return sourceLines.get(line).getSourceLine();
    }

    public String getFunctionName() {
        return environmentStack.peek().getFunctionName();
    }

    public int getStartLine() {
        return environmentStack.peek().getStartLine();
    }

    public int getEndLine() {
        return environmentStack.peek().getEndLine();
    }

    public int getCurrentLine() {
        return environmentStack.peek().getCurrentLine();
    }

    public Set<String> getVariables() {
        return environmentStack.peek().table.keys();
    }

    public Integer getOffset(String offset) {
        return (Integer) environmentStack.peek().table.get(offset) + peekFramePointers();
    }

    public ArrayList<BreakPoints> getSourceLines() {
        return sourceLines;
    }

    public void setCurrentLine(int lineNumber) {
        environmentStack.peek().setCurrentLine(lineNumber);
    }

    public boolean isBreakPointSet(int line) {
        return sourceLines.get(line).isBreakPointSet();
    }

    public boolean isLegalBreakPoint(int offset) {
        return program.isLegalBreakPoint(offset);
    }

    public void setIsQuit(boolean quit) {
        isQuit = quit;
    }

    public boolean isQuit() {
        return isQuit;
    }

    public int envSize() {
        return envSize;
    }

    public int currentEnvSize() {
        return environmentStack.size();
    }

    public ArrayDeque<FunctionEnvironmentRecord> getEnvironmentStack() {
        return environmentStack;
    }

    public void setTraceStatus(boolean traceStatus) {
        this.traceStatus = traceStatus;
    }
    
    public boolean getTraceStatus() {
        return traceStatus;
    }

    public String getIntrinsicFunctionName() {
        return intrinsicFunctionName;
    }
}
