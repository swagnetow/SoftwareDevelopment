package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * CALL transfers control to the indicated function.
 *
 */
public class CallCode extends ByteCode {
    private int symbolicAddress;
    private String callCodeArg;
    private int numArgs;

    public void init(ArrayList<String> byteCodeArgs) {
        callCodeArg = byteCodeArgs.get(0);
    }

    public void execute(VirtualMachine vm) {
        vm.setReturnAddress();
        vm.setPC(symbolicAddress);
        numArgs = vm.getNumArgs();
    }

    @Override
    public void setSymbolicAddress(int address) {
        symbolicAddress = address;
    }

    @Override
    public String getArg() {
        return callCodeArg;
    }

    @Override
    public String toString() {
        return "CALL " + callCodeArg;
    }
}
