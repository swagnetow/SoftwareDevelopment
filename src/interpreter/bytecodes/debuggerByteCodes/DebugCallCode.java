package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.CallCode;
import interpreter.debugger.DebugVM;

public class DebugCallCode extends CallCode {
    @Override
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        DebugVM dvm = (DebugVM) vm;

        dvm.addFunction();
    }
}
