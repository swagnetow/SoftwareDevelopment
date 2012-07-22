package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ReturnCode;
import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;

public class DebugReturnCode extends ReturnCode {
    @Override
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        DebugVM dvm = (DebugVM) vm;

        if(dvm.getTraceStatus()) {
            DebugUI.trace(true);
        }

        dvm.popRecord();
    }
}
