package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import java.util.ArrayList;

public class Help extends Command {
    @Override
    public void init(ArrayList<String> helpArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        System.out.println("?: Displays this help menu.");
        System.out.println("s: Sets a new breakpoint. Each argument sets a breakpoint on a line. " +
                           "Takes multiple arguments.");
        System.out.println("r: Clears all breakpoints if no arguments are given. " +
                           "Each argument added clears the breakpoints on the given line numbers.");
        System.out.println("l: List all breakpoints that are set.");
        System.out.println("df: Displays the current function.");
        System.out.println("dv: Displays the current variables.");
        System.out.println("ds: Displays the current call stack.");
        System.out.println("c: Continues the execution of the program.");
        System.out.println("o: Step out.");
        System.out.println("i: Step in.");
        System.out.println("v: Step over.");
        System.out.println("t: Stes function tracing.");
        System.out.println("q: Quits execution of the program.");
    }
}
