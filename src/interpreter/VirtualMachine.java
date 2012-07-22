package interpreter;

import interpreter.bytecodes.ByteCode;
import java.util.*;

/*
 * Where all of the calls to the RunTimeStack are made. VirtualMachine "owns" the processes
 * of RunTimeStack and therefore is called if the RunTimeStack needs to be accessed. This
 * is so that encapsulation is NOT broken.
 *
 */
public class VirtualMachine {
    protected RunTimeStack runStack = new RunTimeStack();
    protected int pc = 0;
    protected ArrayDeque<Integer> returnAddress = new ArrayDeque();
    protected boolean isRunning = true;
    protected Program program;
    private boolean dumpStatus = false;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);

            if(dumpStatus == true && !code.getClass().getSimpleName().equals("DumpCode")) {
                System.out.println(code);
                runStack.dump();
            }

            pc++;
        }
    }

    public int peek() {
        return runStack.peek();
    }

    public int pop() {
        return runStack.pop();
    }

    public int push(int i) {
        return runStack.push(i);
    }

    public void newFrameAt(int offset) {
        runStack.newFrameAt(offset);
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int store(int offset) {
        return runStack.store(offset);
    }

    public int load(int offset) {
        return runStack.load(offset);
    }

    public Integer push(Integer i) {
        return runStack.push(i);
    }

    public int getStackSize() {
        return runStack.size();
    }

    public void setReturnAddress() {
        returnAddress.push(new Integer(pc));
    }

    public int getReturnAddress() {
        return returnAddress.pop();
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public int getPC() {
        return pc;
    }

    public void setDumpStatus(boolean dumpStatus) {
        this.dumpStatus = dumpStatus;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setNumArgs(int numArgs) {
        runStack.setNumArgs(numArgs);
    }

    public int getNumArgs() {
        return runStack.getNumArgs();
    }

    public int getElementAt(int element) {
        return runStack.getElementAt(element);
    }

    public int peekFramePointers() {
        return runStack.peekFramePointers();
    }
}
