package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class StepOver extends Command {
    @Override
    public void init(ArrayList<String> stepOverArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        dvm.setIsRunning(true);
        dvm.setStep("over");
        dvm.executeProgram();
        dvm.setStep("");

        if(!dvm.isQuit()) {
            DebugUI.displayCurrentFunction();
        }
        else {
            System.out.println("Execution halted.");
        }
    }
}
