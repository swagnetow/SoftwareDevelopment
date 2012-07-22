package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * RETURN returns from the current function.
 *
 */
public class ReturnCode extends ByteCode {
    private String returnCodeArg = new String();

    public void init(ArrayList<String> byteCodeArgs) {
        if(!byteCodeArgs.isEmpty()) {
            returnCodeArg = byteCodeArgs.get(0);
        }
    }

    public void execute(VirtualMachine vm) {
        int returnAddress = vm.getReturnAddress();

        vm.popFrame();
        vm.setPC(returnAddress);
    }

    @Override
    public String toString() {
        if(returnCodeArg.isEmpty()) {
            return "RETURN";
        }
        else {
            StringTokenizer baseId = new StringTokenizer(returnCodeArg, "<<" + ">>");

            return "RETURN " + returnCodeArg + "     exit " + baseId.nextToken() + ": " + baseId.nextToken();
        }
    }
}
