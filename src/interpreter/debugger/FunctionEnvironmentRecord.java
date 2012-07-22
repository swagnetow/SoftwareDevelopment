package interpreter.debugger;

import interpreter.debugger.Table;

public class FunctionEnvironmentRecord {
    protected Table table;
    private int startLine;
    private int endLine;
    private int currentLine;
    private String functionName;

    public FunctionEnvironmentRecord() {
        table = new Table();
        table.beginScope();
    }

    public void enter(String id, int offset) {
        table.put(id, offset);
    }

    public void setStartLine(int line) {
        startLine = line;
    }

    public void setEndLine(int line) {
        endLine = line;
    }

    public void setCurrentLine(int line) {
        currentLine = line;
    }

    public void setFunctionName(String name) {
        functionName = name;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public String getFunctionName() {
        return functionName;
    }
}
