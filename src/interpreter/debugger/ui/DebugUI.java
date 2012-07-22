package interpreter.debugger.ui;

import interpreter.debugger.DebugVM;
import interpreter.debugger.ui.commands.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DebugUI {
    public static DebugVM dvm;
    public static int currentLine;

    public static void display(DebugVM debug) {
        dvm = debug;
        CommandTable.init();
        printSourceLines();

        System.out.println();

        while(!dvm.isQuit()) {
            ArrayList<String> commandArgs = new ArrayList<String>();
            Command cmd = null;

            try {
                System.out.println("Enter ? for help.");
                System.out.print("> ");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String commandInput = input.readLine().toLowerCase();
                StringTokenizer commandTokens = new StringTokenizer(commandInput);
                String commandName = commandTokens.nextToken();
                String cmdName = CommandTable.get(commandName);

                if(!cmdName.equals("Invalid")) {
                    try {
                        cmd = (Command) Class.forName("interpreter.debugger.ui.commands." + cmdName).newInstance(); 
                    }
                    catch(Exception e) {
                        System.out.println(e);
                    }
    
                    while(commandTokens.hasMoreTokens()) {
                        commandArgs.add(commandTokens.nextToken());
                    }
    
                    cmd.init(commandArgs);
                    cmd.execute(dvm);
                    System.out.println();
                }
                else {
                    System.out.println("Enter ? for help.\n");
                }
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void printSourceLines() {
        for(int lineNumber = 1; lineNumber < dvm.getSourceLines().size(); lineNumber++) {
            System.out.println("  " + lineNumber + ". " + dvm.getSourceLines().get(lineNumber).getSourceLine());
        }
    }

    public static void listBreakPoints() {
        for(int lineNumber = 1; lineNumber < dvm.getSourceLines().size(); lineNumber++) {
            if(dvm.isBreakPointSet(lineNumber)) {
                System.out.println("Breakpoint is set on line " + lineNumber + ".");
            }
        }
    }

    public static void displayCurrentFunction() {
        int start = dvm.getStartLine();
        int end = dvm.getEndLine();
        int current = dvm.getCurrentLine();
        String functionName = dvm.getIntrinsicFunctionName();

        /*
        System.out.println("start line: " + start);
        System.out.println("end line: " + end);
        System.out.println("current line: " + current);
        System.out.println("function name: " + functionName);
        */

        if(start > 0 && end > 0 && current > 0) {
            System.out.println("Current function:");

            for(int lineNumber = start; lineNumber <= end; lineNumber++) {
                if(dvm.isBreakPointSet(lineNumber)) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
    
                System.out.print(lineNumber + ". " + dvm.getSourceLine(lineNumber));
    
                if(lineNumber == current) {
                    currentLine = current;
                    System.out.print(" <----");
                }
    
                System.out.print("\n");
            }
        }
        else if(start < 0 && end < 0) {
            System.out.println("***** " + functionName + " *****");
        }
        else {
            System.out.println("Current function:");

            for(int lineNumber = 1; lineNumber < dvm.getSourceLines().size(); lineNumber++) {
                if(dvm.isBreakPointSet(lineNumber)) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }

                System.out.print(lineNumber + ". " + dvm.getSourceLine(lineNumber));

                if(currentLine != current && lineNumber == currentLine) {
                    System.out.print(" <----");
                }

                System.out.print("\n");
            }
        }
    }

    public static void displayVariables() {
        if(!dvm.getVariables().isEmpty()) {
            for(String var: dvm.getVariables()) {
                System.out.println(var + " = " + dvm.getElementAt(dvm.getOffset(var)));
            }
        }
        else {
            System.out.println("No variables to display.");
        }
    }

    public static void trace(boolean isExit) {
        for(int space = 0; space < dvm.currentEnvSize(); space++) {
            System.out.print(" ");
        }

        if(isExit) {
            int value = dvm.peek();

            System.out.print("exit: " + dvm.getFunctionName() + ": " + value);
        }
        else {
            String args = "";

            for(int index = dvm.peekFramePointers(); index < dvm.getStackSize(); index++) {
                args += dvm.getElementAt(index);

                if(index > dvm.getStackSize()) {
                    args += ",";
                }
            }

            System.out.print(dvm.getFunctionName() + "(" + args + ")");
        }

        System.out.print("\n");
    }
}
