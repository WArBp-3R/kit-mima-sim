public class MIMAInstruction {
    //fields - pair value
    private String command;
    private int value;

    //constructors
    MIMAInstruction(String command, int value) {
        this.command = command;
        this.value = MIMA.getMIMAValue(value);
    }

    MIMAInstruction(String command) { this(command, 0); }
    MIMAInstruction() { this("", 0); }

    //methods
    public static void read(MIMA m, MIMAInstruction instruction) {
        String cmdName = instruction.command;
        int paramValue = instruction.value;

        switch (cmdName) {
            case "": m.NULL();
                break;
            case "LDC": m.LDC(paramValue);
                break;
            case "LDV": m.LDV(paramValue);
                break;
            case "STV": m.STV(paramValue);
                break;
            case "LDIV": m.LDIV(paramValue);
                break;
            case "STIV": m.STIV(paramValue);
                break;
            case "ADD": m.ADD(paramValue);
                break;
            case "AND": m.AND(paramValue);
                break;
            case "OR": m.OR(paramValue);
                break;
            case "XOR": m.XOR(paramValue);
                break;
            case "NOT": m.NOT();
                break;
            case "RAR": m.RAR();
                break;
            case "EQL": m.EQL(paramValue);
                break;
            case "JMP": m.JMP(paramValue);
                break;
            case "JMN": m.JMN(paramValue);
                break;
            case "HALT": m.HALT();
                break;
            default: m.NULL();
        }
    }
}
