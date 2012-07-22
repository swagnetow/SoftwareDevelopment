package interpreter.debugger.ui.commands;

import interpreter.debugger.DebugVM;
import interpreter.debugger.FunctionEnvironmentRecord;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class DisplayCallStack extends Command {
    @Override
    public void init(ArrayList<String> displayCallStackArgs) {
    }

    @Override
    public void execute(DebugVM dvm) {
        ArrayDeque<FunctionEnvironmentRecord> environmentStackCopy = dvm.getEnvironmentStack().clone();
        ArrayList<FunctionEnvironmentRecord> environmentStackList = new ArrayList<FunctionEnvironmentRecord>();

        while(!environmentStackCopy.isEmpty()) {
            environmentStackList.add(environmentStackCopy.removeFirst());
        }

        for(int i = 0; i <= dvm.getEnvironmentStack().size()-1; i++) {
            FunctionEnvironmentRecord fer = environmentStackList.get(i);

            System.out.println(fer.getFunctionName() + ": " + fer.getCurrentLine());
        }
    }
}
