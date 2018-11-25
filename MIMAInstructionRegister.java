class MIMAInstructionRegister {
    //fields
    private int instructionLength;
    private MIMAInstruction[] instructionRegister;

    //constructors
    MIMAInstructionRegister() {
        instructionLength = 0;
        instructionRegister = new MIMAInstruction[(int)(Math.pow(2, MIMA.adressBitSize))];
    }

    MIMAInstructionRegister(MIMAInstruction[] instructions) {
        this();
        setInstructions(instructions);
    }

    //methods
    public MIMAInstruction getInstructionAt(int adr) { return instructionRegister[adr]; }
    public void setInstructions(MIMAInstruction[] instructions) {
        instructionLength = instructions.length;
        for (int i=0; i<instructionLength; ++i)
            instructionRegister[i] = instructions[i];
    }

    public void addInstructions(MIMAInstruction[] instructions) {
        int previousLength = instructionLength-instructions.length;
        instructionLength += instructions.length;
        for (int i=previousLength; i<instructionLength; ++i) {
            instructionRegister[i] = instructions[i];
        }
    }
}
