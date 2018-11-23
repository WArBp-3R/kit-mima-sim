public class MIMA {
    private Bit24 accumulator;
    public Bit24[] memoryRegister;
    public MIMAInstruction[] instructionRegister;
    private int instructionIterator;
    private boolean haltProcess;

    MIMA() {
        accumulator = new Bit24();
        memoryRegister = new Bit24[(int)(Math.pow(2, 20))];
        instructionRegister = new MIMAInstruction[(int)(Math.pow(2, 20))];
    }

    MIMA(MIMAInstruction[] initiativeInstructions) {
        this();
        for (int i=0; i<initiativeInstructions.length; ++i)
            instructionRegister[i] = initiativeInstructions[i];
    }

    public void startProcess() {
        haltProcess = false;
        instructionIterator = 0;
        while (!haltProcess) {
            MIMAInstruction.read(this, instructionRegister[instructionIterator]);
            instructionIterator++;
        }
    }

    public int getMemValAt(int memAdr) {
        return memoryRegister[memAdr].getValue();
    }

    //MIMA-commands
    public void LDC(int c)      { accumulator.setValue(c); }
    public void LDV(int memAdr) { accumulator.setValue(getMemValAt(memAdr)); }
    public void STV(int memAdr) {
        if (memoryRegister[memAdr] == null)
            memoryRegister[memAdr] = new Bit24();
        memoryRegister[memAdr].setValue(accumulator.getValue());
    }

    public void LDIV(int memAdr) {
        LDV(getMemValAt(memAdr));
        LDV(getMemValAt(memAdr));
    }

    public void STIV(int memAdr) {
        LDV(getMemValAt(memAdr));
        STV(getMemValAt(memAdr));
    }

    public void ADD(int memAdr) { accumulator.setValue(accumulator.getValue() + getMemValAt(memAdr)); }
    public void AND(int memAdr) { accumulator.setValue(accumulator.getValue() & getMemValAt(memAdr)); }
    public void OR (int memAdr) { accumulator.setValue(accumulator.getValue() | getMemValAt(memAdr)); }
    public void XOR(int memAdr) { accumulator.setValue(accumulator.getValue() ^ getMemValAt(memAdr)); }
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

    public void EQL(int memAdr) { accumulator.setValue(accumulator.getValue() == getMemValAt(memAdr) ? -1 : 0); }
    public void JMP(int instructionAdr){ instructionIterator = instructionAdr - 1;}

    public void JMN(int instructionAdr){
        if (accumulator.getValue() < 0)
            JMP(instructionAdr);
        else
            NULL();
    }

    public void HALT() { haltProcess = true; }

    //user-defined command
    public void NULL() {/*does nothing, used for default cases when reading instructions*/}

}
