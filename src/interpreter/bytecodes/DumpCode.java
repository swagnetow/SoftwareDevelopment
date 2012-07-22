package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * DUMP tells the Interpreter whether to dump the ByteCodes or not.
 *
 */
public class DumpCode extends ByteCode {
    private String dumpArg; 
    private boolean status = false;

    public void init(ArrayList<String> byteCodeArgs) {
        dumpArg = byteCodeArgs.get(0);
    }

    public void execute(VirtualMachine vm) {
        if(dumpArg.equals("ON")) {
            status = true;
            vm.setDumpStatus(status);
        }
        else {
            status = false;
            vm.setDumpStatus(status);
        }
    }
}
