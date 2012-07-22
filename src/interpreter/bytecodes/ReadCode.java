package interpreter.bytecodes;

import interpreter.VirtualMachine;
import javax.swing.JOptionPane;
import java.util.*;

/*
 * From the reader:
 *
 * Reads an integer; prompt the user for input and put the
 * the value just read on top of the stack.
 *
 */
public class ReadCode extends ByteCode {
    @Override
    public void init(ArrayList<String> byteCodeArgs) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        String input = JOptionPane.showInputDialog(null, "Enter a number:");

        System.out.println(input);
        vm.push(Integer.parseInt(input));
    }

    @Override
    public String toString() {
        return "READ";
    }
}
