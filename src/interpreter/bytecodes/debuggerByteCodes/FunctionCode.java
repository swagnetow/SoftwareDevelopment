package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class FunctionCode extends ByteCode {
    String functionName;
    int startLine;
    int endLine;

    @Override
    public void init(ArrayList<String> functionCodeArgs) {
        functionName = functionCodeArgs.get(0);
        startLine = Integer.parseInt(functionCodeArgs.get(1));
        endLine = Integer.parseInt(functionCodeArgs.get(2));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebugVM dvm = (DebugVM) vm;

        dvm.setFunction(functionName, startLine, endLine);

        if(dvm.getNextByteCodeName().equals("FORMAL")) {
            dvm.setIsRunning(false);
        }

        if(dvm.getTraceStatus()) {
            DebugUI.trace(false);
        }
    }

    @Override
    public String getArg() {
        return functionName;
    }

    @Override
    public String toString() {
        return "FUNCTION " + functionName + " " + startLine + " " + endLine;
    }
}
