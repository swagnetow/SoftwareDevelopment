package interpreter.debugger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SourceReader {
    public static ArrayList<BreakPoints> read(String sourceFile) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(sourceFile));
        ArrayList<BreakPoints> sourceLines = new ArrayList<BreakPoints>();
        String nextLine = file.readLine();

        // Add empty BreakPoint line
        sourceLines.add(new BreakPoints(" ", false));

        while(nextLine != null) {
            sourceLines.add(new BreakPoints(nextLine, false));
            nextLine = file.readLine();
        }

        return sourceLines;
    }
}
