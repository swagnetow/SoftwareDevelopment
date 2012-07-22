package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class Continue extends Command {
    @Override
    public void init(ArrayList<String> continueArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        dvm.setIsRunning(true);
        dvm.executeProgram();

        if(!dvm.isQuit()) {
            DebugUI.displayCurrentFunction();
        }
        else {
            System.out.println("Execution halted.");
        }
    }
}
