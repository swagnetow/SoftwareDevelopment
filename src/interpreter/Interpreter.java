package interpreter;

import interpreter.debugger.Debugger;
import java.io.*;

/**
 * <pre>
 *
 *
 *
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *
 *
 *
 * </pre>
 */
public class Interpreter {
    private ByteCodeLoader bcl;

    public Interpreter(String codeFile) {
        CodeTable.init();

        try {
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }

    void run() throws IOException {
        Program program = bcl.loadCodes();
        VirtualMachine vm;

        vm = new VirtualMachine(program);
        vm.executeProgram();
    }

    public static void main(String args[]) {
        if(args.length == 0) {
            System.out.println("*** Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }

        if(args[0].equals("-d")) {
            Debugger debugger = new Debugger(args[1]);

            try {
                debugger.run();
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
        else {
            Interpreter interpreter = new Interpreter(args[0]);

            try {
                interpreter.run();
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

    }
}
