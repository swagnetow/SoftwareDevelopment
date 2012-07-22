package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class ClearBreakPoints extends Command {
    ArrayList<Integer> lineNumbers = new ArrayList<Integer>();

    @Override
    public void init(ArrayList<String> clearBreakPointsArgs) {
        for(String breakPoint: clearBreakPointsArgs) {
            lineNumbers.add(Integer.parseInt(breakPoint));
        }
    }

    @Override
    public void execute(DebugVM dvm) {
        if(lineNumbers.isEmpty()) {
            for(int i = 0; i < dvm.getSourceLines().size(); i++) {
                dvm.getSourceLines().get(i).setBreakPoint(false);
            }

            System.out.println("All breakpoints cleared.");
        }
        else {
            for(int lineNumber: lineNumbers) {
                System.out.println("Cleared breakpoint on line " + lineNumber + ".");
                dvm.getSourceLines().get(lineNumber).setBreakPoint(false);
            }
        }
    }
}
