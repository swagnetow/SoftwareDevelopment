package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * LABEL is a target for branches.
 *
 */
public class LabelCode extends ByteCode {
    private String labelCodeArg = new String();
    private int symbolicAddress;

    public void init(ArrayList<String> byteCodeArgs) {
        labelCodeArg = byteCodeArgs.get(0);
    }

    public void execute(VirtualMachine vm) {
    }

    @Override
    public void setSymbolicAddress(int address) {
        symbolicAddress = address;
    }

    @Override
    public String getArg() {
        return labelCodeArg;
    }

    @Override
    public String toString() {
        return "LABEL " + labelCodeArg;
    }
}
