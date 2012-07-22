package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * LOAD pushes the value in the slot which is an offset onto the
 * top of the stack in order to initialize the variable i to 0 and
 * reserve space on the runtime stack for i.
 *
 */
public class LoadCode extends ByteCode {
    ArrayList<String> loadCodeArgs = new ArrayList<String>();

    @Override
    public void init(ArrayList<String> byteCodeArgs) {
        for(String bca: byteCodeArgs) {
            loadCodeArgs.add(bca);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        int offset = Integer.parseInt(loadCodeArgs.get(0));

        vm.load(offset);
    }

    @Override
    public String toString() {
        return "LOAD " + loadCodeArgs.get(0) + " " + loadCodeArgs.get(1) + "     load " + loadCodeArgs.get(1);
    }
}
