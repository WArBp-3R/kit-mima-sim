public class MIMAMemoryRegister {
    //fields
    private int[] memoryRegister;

    //constructors
    MIMAMemoryRegister() {
        memoryRegister = new int[(int)(Math.pow(2, MIMA.adressBitSize))];
    }

    //methods
    public int getValueAt(int adr) { return memoryRegister[adr]; }
    public void setValueAt(int adr, int val) { memoryRegister[adr] = MIMA.getMIMAValue(val); }
}
