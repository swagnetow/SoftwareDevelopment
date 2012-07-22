package interpreter.debugger.ui.commands;

import interpreter.debugger.BreakPoints;
import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class SetBreakPoints extends Command {
    public ArrayList<Integer> lineNumbers = new ArrayList<Integer>();

    @Override
    public void init(ArrayList<String> setBreakPointsArgs) {
        for(String lineNumber: setBreakPointsArgs) {
            lineNumbers.add(Integer.parseInt(lineNumber));
        }
    }

    @Override
    public void execute(DebugVM dvm) {
        if(!lineNumbers.isEmpty()) {
            for(int lineNumber: lineNumbers) {
                if(lineNumber <= dvm.getSourceLines().size() && lineNumber > 0) {
                    BreakPoints bp = dvm.getSourceLines().get(lineNumber);
                    String line = bp.getSourceLine();
    
                    if(dvm.isLegalBreakPoint(lineNumber)) {
                        System.out.println("Breakpoint set on line " + lineNumber + ".");
                        dvm.getSourceLines().get(lineNumber).setBreakPoint(true);
                    }
                    else {
                        System.out.println("Cannot set breakpoint at line " + lineNumber + " because it is not a legal breakpoint.");
                    }
                }
                else {
                    System.out.println("Cannot set breakpoint at line " + lineNumber + " because that line number does not exist.");
                }
            }
        }
        else {
            System.out.println("No arguments were given. At least one integer argument should be entered.");
        }
    }
}
