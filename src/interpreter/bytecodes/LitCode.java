package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * LIT loads a literal value onto the stack.
 *
 */
public class LitCode extends ByteCode {
    protected ArrayList<String> litCodeArgs = new ArrayList<String>();
    protected String id;
    protected int value;

    public void init(ArrayList<String> byteCodeArgs) {
        for(String bca: byteCodeArgs) {
            litCodeArgs.add(bca);
        }
    }

    public void execute(VirtualMachine vm) {
        if(litCodeArgs.size() == 2) {
            id = litCodeArgs.get(1);
        }
        else {
            id = "";
        }
        
        value = Integer.parseInt(litCodeArgs.get(0));

        vm.push(value);
    }

    @Override
    public String toString() {
        if(litCodeArgs.size() == 1) {
            return "LIT " + litCodeArgs.get(0);
        }
        else {
            return "LIT " + litCodeArgs.get(0) + " " + litCodeArgs.get(1) + "      int " + litCodeArgs.get(1);
        }
    }
}
