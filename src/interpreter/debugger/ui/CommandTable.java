package interpreter.debugger.ui;

import java.util.HashMap;

public class CommandTable {
    public static HashMap<String, String> commands = new HashMap<String, String>();

    public static String get(String command) {
        if(command.equals(null)) {
            return "Invalid";
        }

        if(commands.containsKey(command)) {
            return commands.get(command);
        }
        else {
            return "Invalid";
        }
    }

    public static void init() {
        commands.put("?", "Help");
        commands.put("s", "SetBreakPoints");
        commands.put("r", "ClearBreakPoints");
        commands.put("l", "ListBreakPoints");
        commands.put("c", "Continue");
        commands.put("df", "DisplayFunction");
        commands.put("dv", "DisplayVariables");
        commands.put("ds", "DisplayCallStack");
        commands.put("o", "StepOut");
        commands.put("i", "StepInto");
        commands.put("v", "StepOver");
        commands.put("t", "Trace");
        commands.put("q", "Quit");
    }
}
