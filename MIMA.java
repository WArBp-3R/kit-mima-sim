public class MIMA {
    //MIMA config
    static final int adressBitSize = 20; //default 20 bit - maximum 32 bit (int)
    static final int valueBitSize = 24; //default 24 bit - maximum 32 bit (int)

    static final int MAX_VALUE = (int)(Math.pow(2, valueBitSize-1) -1);
    static final int VALUE_BIT_RANGE = (int)Math.pow(2, valueBitSize);

    //processor fields
    private int accumulator;
    public MIMAMemoryRegister memoryRegister;
    public MIMAInstructionRegister instructionRegister;

    //simulation-handling fields
    private int instructionIterator;
    private boolean haltProcess;

    //constructors
    MIMA() {
        resetMemory();
        resetInstructions();
        initProcess();
    }

    MIMA(MIMAInstruction[] instructions) {
        this();
        instructionRegister = new MIMAInstructionRegister(instructions);
    }

    //methods
    public static int getMIMAValue(int x) {
        x %= VALUE_BIT_RANGE;
        if (x < 0)
            x = VALUE_BIT_RANGE + x;
        if (x > MAX_VALUE)
            x = -(VALUE_BIT_RANGE - x);
        return x;
    }

    private void initProcess() {
        haltProcess = false;
        instructionIterator = 0;
    }

    public void resetMemory() { memoryRegister = new MIMAMemoryRegister(); }
    public void resetInstructions() { instructionRegister = new MIMAInstructionRegister(); }
    public void startProcess() {
        initProcess();
        while (!haltProcess) {
            MIMAInstruction.read(this, instructionRegister.getInstructionAt(instructionIterator));
            instructionIterator++;
        }
    }

    //wrappers
    public int getMemValAt(int memAdr) { return memoryRegister.getValueAt(memAdr); }
    public void setMemValAt(int memAdr, int val) {memoryRegister.setValueAt(memAdr, val); }

    //MIMA-commands
    public void LDC(int c)      { accumulator = getMIMAValue(c); }
    public void LDV(int memAdr) { accumulator = getMemValAt(memAdr); }
    public void STV(int memAdr) { setMemValAt(memAdr, accumulator); }
    public void LDIV(int memAdr){ LDV(getMemValAt(memAdr)); }
    public void STIV(int memAdr){ STV(getMemValAt(memAdr)); }
    public void ADD(int memAdr) { accumulator = getMIMAValue(accumulator + getMemValAt(memAdr)); }
    public void AND(int memAdr) { accumulator = getMIMAValue(accumulator & getMemValAt(memAdr)); }
    public void OR (int memAdr) { accumulator = getMIMAValue(accumulator | getMemValAt(memAdr)); }
    public void XOR(int memAdr) { accumulator = getMIMAValue(accumulator ^ getMemValAt(memAdr)); }
    public void NOT()           { accumulator = ~accumulator;}
    public void RAR() {
        int accumulatorValue = accumulator;
        int reversedValue = 0;
        for (int i=0; i<valueBitSize; ++i) {
            reversedValue += (accumulatorValue % 2)*Math.pow(10, valueBitSize-i);
            accumulatorValue /= 2;
        }
        accumulator = reversedValue;
    }

    public void EQL(int memAdr) {
        accumulator = accumulator == getMemValAt(memAdr) ? -1 : 0;
    }

    public void JMP(int instructionAdr){ instructionIterator = instructionAdr - 1;}
    public void JMN(int instructionAdr){
        if (accumulator < 0)
            JMP(instructionAdr);
        else
            NULL();
    }

    public void HALT() { haltProcess = true; }

    //simulation-related MIMA-command
    public void NULL() {/*does nothing, used for default cases when reading instructions*/}
}
