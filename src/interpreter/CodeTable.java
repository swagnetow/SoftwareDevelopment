package interpreter;

import java.util.HashMap;

/*
 * From the reader:
 *
 * Used by the ByteCodeLoader.
 *
 * Contains a HashMap with keys being the bytecode strings and values being the name
 * of the corresponding class.
 *
 */
public class CodeTable {
    public static HashMap<String, String> byteCodes = new HashMap<String, String>();

    public static String get(String code) {
        return byteCodes.get(code);
    }

    public static void init() {
        byteCodes.put("HALT", "HaltCode");
        byteCodes.put("POP", "PopCode");
        byteCodes.put("FALSEBRANCH", "FalseBranchCode");
        byteCodes.put("GOTO", "GotoCode");
        byteCodes.put("STORE", "StoreCode");
        byteCodes.put("LOAD", "LoadCode");
        byteCodes.put("LIT", "LitCode");
        byteCodes.put("ARGS", "ArgsCode");
        byteCodes.put("CALL", "CallCode");
        byteCodes.put("RETURN", "ReturnCode");
        byteCodes.put("BOP", "BopCode");
        byteCodes.put("READ", "ReadCode");
        byteCodes.put("WRITE", "WriteCode");
        byteCodes.put("LABEL", "LabelCode");
        byteCodes.put("DUMP", "DumpCode");
    }

    public static void debugInit() {
        byteCodes.put("HALT", "HaltCode");
        byteCodes.put("POP", "debuggerByteCodes.DebugPopCode");
        byteCodes.put("FALSEBRANCH", "FalseBranchCode");
        byteCodes.put("GOTO", "GotoCode");
        byteCodes.put("STORE", "StoreCode");
        byteCodes.put("LOAD", "LoadCode");
        byteCodes.put("LIT", "debuggerByteCodes.DebugLitCode");
        byteCodes.put("ARGS", "ArgsCode");
        byteCodes.put("CALL", "debuggerByteCodes.DebugCallCode");
        byteCodes.put("RETURN", "debuggerByteCodes.DebugReturnCode");
        byteCodes.put("BOP", "BopCode");
        byteCodes.put("READ", "ReadCode");
        byteCodes.put("WRITE", "WriteCode");
        byteCodes.put("LABEL", "LabelCode");
        byteCodes.put("FORMAL", "debuggerByteCodes.FormalCode");
        byteCodes.put("FUNCTION", "debuggerByteCodes.FunctionCode");
        byteCodes.put("LINE", "debuggerByteCodes.LineCode");
    }
}
