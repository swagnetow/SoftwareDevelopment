package interpreter.debugger;

import interpreter.ByteCodeLoader;
import interpreter.CodeTable;
import interpreter.Program;
import interpreter.debugger.ui.DebugUI;
import java.io.IOException;
import java.util.ArrayList;

public class Debugger {
    private ArrayList<BreakPoints> sourceLines;
    private ByteCodeLoader bcl;
    private int sourceLinesSize;

    public Debugger(String codeFile) {
        // Calls the debugger version of the init method because debugger has 
        // bytecodes that are not in interpreter.
        CodeTable.debugInit();

        try {
            String sourceFile = codeFile + ".x";
            codeFile += ".x.cod";
            bcl = new ByteCodeLoader(codeFile);
            sourceLines = SourceReader.read(sourceFile);
            sourceLinesSize = sourceLines.size()-1;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void run() throws IOException {
        Program program = bcl.loadCodes();
        DebugVM dvm = new DebugVM(program, sourceLines);

        DebugUI.display(dvm);
    }
}
