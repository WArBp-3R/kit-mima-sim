public class MIMA {
    //processor fields
    private Bit24 accumulator;
    public Bit24[] memoryRegister;
    public MIMAInstruction[] instructionRegister;

    //simulation-handling fields
    private int instructionIterator;
    private int instructionLength;
    private boolean haltProcess;

    //construction
    MIMA() {
        reset();
        initProcess();
    }

    MIMA(MIMAInstruction[] instructions) {
        this();
        setInstructions(instructions);
    }

    //process and initalization methods
    private void initProcess() {
        accumulator = new Bit24();

        haltProcess = false;
        instructionIterator = 0;
    }

    public void reset() {
        clearMemory();
        clearInstructions();
    }

    public void startProcess() {
        initProcess();

        while (!haltProcess) {
            MIMAInstruction.read(this, instructionRegister[instructionIterator]);
            instructionIterator++;
        }
    }


    //instruction methods
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

    public void clearInstructions() {
        instructionLength = 0;
        instructionRegister = new MIMAInstruction[(int)(Math.pow(2, 20))];
    }


    //memory methods
    public void clearMemory() {
        memoryRegister = new Bit24[(int)(Math.pow(2, 20))];
    }

    public int getMemoryValueAt(int memAdr) {
        return memoryRegister[memAdr].getValue();
    }

    public void setMemoryValueAt(int memAdr, int val) {
        int accumulatorValue = accumulator.getValue();
        LDC(val);
        STV(memAdr);
        LDC(accumulatorValue);
    }

    //MIMA-commands
    public void LDC(int c)      { accumulator.setValue(c); }
    public void LDV(int memAdr) { accumulator.setValue(getMemoryValueAt(memAdr)); }
    public void STV(int memAdr) {
        if (memoryRegister[memAdr] == null)
            memoryRegister[memAdr] = new Bit24();
        memoryRegister[memAdr].setValue(accumulator.getValue());
    }

    public void LDIV(int memAdr) {
        LDV(getMemoryValueAt(memAdr));
        LDV(getMemoryValueAt(memAdr));
    }

    public void STIV(int memAdr) {
        LDV(getMemoryValueAt(memAdr));
        STV(getMemoryValueAt(memAdr));
    }

    public void ADD(int memAdr) { accumulator.setValue(accumulator.getValue() + getMemoryValueAt(memAdr)); }
    public void AND(int memAdr) { accumulator.setValue(accumulator.getValue() & getMemoryValueAt(memAdr)); }
    public void OR (int memAdr) { accumulator.setValue(accumulator.getValue() | getMemoryValueAt(memAdr)); }
    public void XOR(int memAdr) { accumulator.setValue(accumulator.getValue() ^ getMemoryValueAt(memAdr)); }
    public void NOT()           { accumulator.setValue(~accumulator.getValue());}

    public void RAR() {
        int accumulatorValue = accumulator.getValue();
        int reversedValue = 0;
        for (int i=0; i<24; ++i) {
            reversedValue += (accumulatorValue % 2)*Math.pow(10, 24-i);
            accumulatorValue /= 2;
        }
        accumulator.setValue(reversedValue);
    }

    public void EQL(int memAdr) { accumulator.setValue(accumulator.getValue() == getMemoryValueAt(memAdr) ? -1 : 0); }
    public void JMP(int instructionAdr){ instructionIterator = instructionAdr - 1;}

    public void JMN(int instructionAdr){
        if (accumulator.getValue() < 0)
            JMP(instructionAdr);
        else
            NULL();
    }

    public void HALT() { haltProcess = true; }

    //simulation-related MIMA-command
    public void NULL() {/*does nothing, used for default cases when reading instructions*/}
}
