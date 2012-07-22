package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * HALT halts the execution of the program.
 *
 */
public class HaltCode extends ByteCode {
    public void init(ArrayList<String> byteCodeArgs) {
    }

    public void execute(VirtualMachine vm) {
        vm.setIsRunning(false);
    }

    @Override
    public String toString() {
        return "HALT";
    }
}
