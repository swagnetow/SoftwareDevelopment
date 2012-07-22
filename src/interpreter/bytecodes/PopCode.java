package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * POP pops n levels of the runtime stack.
 *
 */
public class PopCode extends ByteCode {
    protected int popCodeArg;

    public void init(ArrayList<String> byteCodeArgs) {
        popCodeArg = Integer.parseInt(byteCodeArgs.get(0));
    }

    public void execute(VirtualMachine vm) {
        for(int i = 0; i < popCodeArg; i++) {
            vm.pop();
        }
    }

    @Override
    public String toString() {
        return "POP " + popCodeArg;
    }
}
