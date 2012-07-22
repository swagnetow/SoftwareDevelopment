package interpreter.debugger;

public class BreakPoints {
    private String sourceLine;
    private boolean isBreakPointSet;

    public BreakPoints(String sourceLine, boolean isBreakPointSet) {
        this.sourceLine = sourceLine;
        this.isBreakPointSet = isBreakPointSet;
    }

    public String getSourceLine() {
        return sourceLine;
    }

    public boolean isBreakPointSet() {
        return isBreakPointSet;
    }

    public void setBreakPoint(boolean status) {
        isBreakPointSet = status;
    }
}
