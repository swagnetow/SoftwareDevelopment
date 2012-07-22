package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * WRITE writes the value on top of the stack to output.
 *
 */
public class WriteCode extends ByteCode {
    public void init(ArrayList<String> byteCodeArgs) {
    }

    public void execute(VirtualMachine vm) {
        System.out.println(vm.peek());
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}
