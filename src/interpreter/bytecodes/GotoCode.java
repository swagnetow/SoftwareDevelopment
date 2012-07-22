package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * GOTO jumps to a LABEL.
 *
 */
public class GotoCode extends ByteCode {
    private String gotoCodeArg = new String();
    private int symbolicAddress;

    public void init(ArrayList<String> byteCodeArgs) {
        gotoCodeArg = byteCodeArgs.get(0);
    }

    public void execute(VirtualMachine vm) {
        vm.setPC(symbolicAddress);
    }

    @Override
    public void setSymbolicAddress(int address) {
        symbolicAddress = address;
    }

    @Override
    public String getArg() {
        return gotoCodeArg;
    }

    @Override
    public String toString() {
        return "GOTO " + gotoCodeArg;
    }
}
