package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class StepInto extends Command {
    @Override
    public void init(ArrayList<String> stepInArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        dvm.setIsRunning(true);
        dvm.setStep("into");
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
