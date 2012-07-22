package interpreter.bytecodes.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.LitCode;
import interpreter.debugger.DebugVM;

public class DebugLitCode extends LitCode {
    @Override
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        DebugVM dvm = (DebugVM) vm;

        if(!id.equals("")) {
            dvm.addRecord(id, dvm.getStackSize()-1-vm.peekFramePointers());
        }
    }
}
