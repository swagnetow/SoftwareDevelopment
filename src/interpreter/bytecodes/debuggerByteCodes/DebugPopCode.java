package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.PopCode;
import interpreter.debugger.DebugVM;

public class DebugPopCode extends PopCode {
    @Override
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        DebugVM dvm = (DebugVM) vm;

        dvm.popSymbol(popCodeArg);
    }
}
