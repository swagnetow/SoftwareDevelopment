package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class DisplayFunction extends Command {
    @Override
    public void init(ArrayList<String> displayFunctionArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        DebugUI.displayCurrentFunction();
    }
}
