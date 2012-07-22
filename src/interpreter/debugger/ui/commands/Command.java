package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import java.util.ArrayList;

public abstract class Command {
    public abstract void init(ArrayList<String> commandArgs);
    public abstract void execute(DebugVM dvm);
}
