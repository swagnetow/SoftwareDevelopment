package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import java.util.ArrayList;

public class Quit extends Command {
    @Override
    public void init(ArrayList<String> quitArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        dvm.setIsRunning(false);
        dvm.setIsQuit(true);
        System.out.println("Execution halted.");
    }
}
