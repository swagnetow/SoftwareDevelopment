package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.DebugUI;
import java.util.ArrayList;

public class ListBreakPoints extends Command {
    @Override
    public void init(ArrayList<String> listBreakPointsArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        DebugUI.listBreakPoints();
    }
}
