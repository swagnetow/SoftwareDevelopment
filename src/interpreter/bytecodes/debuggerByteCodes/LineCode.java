package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DebugVM;
import java.util.ArrayList;

public class LineCode extends ByteCode {
    int lineNumber;

    @Override
    public void init(ArrayList<String> lineCodeArgs) {
        lineNumber = Integer.parseInt(lineCodeArgs.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebugVM dvm = (DebugVM) vm;

        dvm.setCurrentLine(lineNumber);

        if(lineNumber > 0) {
            if(dvm.isBreakPointSet(lineNumber)) {
                dvm.setIsRunning(false);
            }
        }
    }

    @Override
    public String getArg() {
        return Integer.toString(lineNumber);
    }

    @Override
    public String toString() {
        return "LINE " + lineNumber;
    }
}
