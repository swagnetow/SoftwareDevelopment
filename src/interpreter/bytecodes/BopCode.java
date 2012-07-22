package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.*;

/*
 * From the reader:
 *
 * BOP pops the top 2 levels of the stack and perform an indicated
 * binary operation.
 *
 */
public class BopCode extends ByteCode {
    private String bopCodeArg;

    public void init(ArrayList<String> byteCodeArgs) {
        bopCodeArg = byteCodeArgs.get(0);
    }

    public void execute(VirtualMachine vm) {
        int first = vm.pop();
        int second = vm.pop();
        int result = 0;

        if(bopCodeArg.equals("+")) {
            result = second + first;
            vm.push(result);
        }
        else if(bopCodeArg.equals("-")) {
            result = second - first;
            vm.push(result);
        }
        else if(bopCodeArg.equals("/")) {
            result = second / first;
            vm.push(result);
        }
        else if(bopCodeArg.equals("*")) {
            result = second * first;
            vm.push(result);
        }
        else if(bopCodeArg.equals("==")) {
            if(second == first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals("!=")) {
            if(second != first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals("<=")) {
            if(second <= first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals(">")) {
            if(second > first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals(">=")) {
            if(second >= first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals("<")) {
            if(second < first) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals("|")) {
            if(second > 0 || first > 0) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
        else if(bopCodeArg.equals("&")) {
            if(second > 0 && first > 0) {
                vm.push(1);
            }
            else {
                vm.push(0);
            }
        }
    }

    public String toString() {
        return "BOP " + bopCodeArg;
    }
}
