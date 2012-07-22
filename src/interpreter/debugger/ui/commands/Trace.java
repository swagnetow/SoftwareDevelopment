package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import java.util.ArrayList;

public class Trace extends Command {
    private boolean traceStatus;

    @Override
    public void init(ArrayList<String> traceArgs) {
        if(traceArgs.get(0).equals("on")) {
            traceStatus = true;
            System.out.println("Trace on.");
        }
        else if(traceArgs.get(0).equals("off")) {
            traceStatus = false;
            System.out.println("Trace off.");
        }
        else {
            System.out.println("Invalid argument. Trace was not turned on or off.");
        }
    }

    @Override
    public void execute(DebugVM dvm) {
        dvm.setTraceStatus(traceStatus);
    }
}
