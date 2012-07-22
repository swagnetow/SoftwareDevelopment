package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * ARGS is used prior to calling a function.
 *
 */
public class ArgsCode extends ByteCode {
    private int argsArg; 

    public void init(ArrayList<String> byteCodeArgs) {
        argsArg = Integer.parseInt(byteCodeArgs.get(0));
    }

    public void execute(VirtualMachine vm) {
        int offset = vm.getStackSize() - argsArg;

        vm.setNumArgs(argsArg);
        vm.newFrameAt(offset);
    }

    @Override
    public String toString() {
        return "ARGS " + argsArg;
    }
}
