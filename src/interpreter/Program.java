package interpreter;

import interpreter.bytecodes.ByteCode;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Receives ByteCodes from ByteCodeLoader and resolves addresses.
 */
public class Program {
    private ArrayList<ByteCode> byteCodeLines;
    private HashMap<String, Integer> labelAddresses;
    private ArrayList<Integer> legalBreakPoints;
    private int counter = 0;

    public Program() {
        byteCodeLines = new ArrayList<ByteCode>();
        labelAddresses = new HashMap<String, Integer>();
        legalBreakPoints = new ArrayList<Integer>();
    }

    public void setByteCodeLines(ByteCode bytecode) {
        byteCodeLines.add(bytecode);

        if(bytecode.getClass().getSimpleName().equals("LabelCode")) {
            labelAddresses.put(bytecode.getArg(), counter);
        }

        if(bytecode.getClass().getSimpleName().equals("LineCode") && Integer.parseInt(bytecode.getArg()) > 0) {
            legalBreakPoints.add(Integer.parseInt(bytecode.getArg()));
        }

        counter++;
    }

    public void resolveAddresses() {
        for(ByteCode bc: byteCodeLines) {
            if(bc.getClass().getSimpleName().equals("DebugCallCode") || bc.getClass().getSimpleName().equals("FalseBranchCode") || bc.getClass().getSimpleName().equals("GotoCode")) {
                bc.setSymbolicAddress(labelAddresses.get(bc.getArg()));
            }
        }
    }
    
    public boolean isLegalBreakPoint(int offset) {
        return legalBreakPoints.contains(offset);
    }

    public ByteCode getCode(int counter) {
        return byteCodeLines.get(counter);
    }

    public int getSize() {
        return byteCodeLines.size();
    }
}
