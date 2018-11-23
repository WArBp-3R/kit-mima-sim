public class Main {
    public static void main(String[] args) {
        //pre-declared memory adresses
        int xMemAdr = 0;
        int yMemAdr = 1;

        //pre-declared instruction points
        int while_InstructionAdr = 2;
        int end_InstructionAdr = 15;

        //process code
        MIMAInstruction[] process = {
            new MIMAInstruction("LDC", 0),
            new MIMAInstruction("STV", yMemAdr),
            new MIMAInstruction("LDC", 0),
            new MIMAInstruction("NOT"),
            new MIMAInstruction("ADD", 0),
            new MIMAInstruction("JMN", end_InstructionAdr),
            new MIMAInstruction("LDC", 0),
            new MIMAInstruction("NOT"),
            new MIMAInstruction("ADD", xMemAdr),
            new MIMAInstruction("AND", xMemAdr),
            new MIMAInstruction("STV", xMemAdr),
            new MIMAInstruction("LDC", 1),
            new MIMAInstruction("ADD", yMemAdr),
            new MIMAInstruction("STV", yMemAdr),
            new MIMAInstruction("JMP", while_InstructionAdr),
            new MIMAInstruction("HALT")
        };

        //init minimal machine
        MIMA mima = new MIMA(process);

        for (int i=0; i<33; ++i) {
            //pre-initialization of memory adresses
            mima.memoryRegister[xMemAdr] = new Bit24(i);

            //start minimal machine process
            mima.startProcess();
            System.out.println(mima.memoryRegister[xMemAdr].getValue());
        }
    }
}

/*6.1
//pre-declared memory adresses
int xMemAdr = 0;
int yMemAdr = 1;
int zMemAdr = 2;

//pre-declared instruction points
int A_InstructionAdr = 2;
int B_InstructionAdr = 6;
int C_InstructionAdr = 14;

//process code
MIMAInstruction[] process = {
    new MIMAInstruction("LDC", 0),
    new MIMAInstruction("STV", zMemAdr),
    new MIMAInstruction("EQL", xMemAdr),
    new MIMAInstruction("NOT"),
    new MIMAInstruction("JMN", B_InstructionAdr),
    new MIMAInstruction("HALT"),
    new MIMAInstruction("LDV", 1),
    new MIMAInstruction("ADD", 2),
    new MIMAInstruction("STV", 2),
    new MIMAInstruction("LDC", 0),
    new MIMAInstruction("NOT"),
    new MIMAInstruction("ADD", 0),
    new MIMAInstruction("STV", 0),
    new MIMAInstruction("LDC", 0),
    new MIMAInstruction("JMP", A_InstructionAdr)
};

//init minimal machine
MIMA mima = new MIMA(process);

//pre-initialization of memory adresses
mima.memoryRegister[xMemAdr] = new Bit24(3);
mima.memoryRegister[yMemAdr] = new Bit24(1337);

//start minimal machine process
mima.startProcess();

//reading results from memory register
int xResult = mima.memoryRegister[xMemAdr].getValue();
int yResult = mima.memoryRegister[yMemAdr].getValue();
int zResult = mima.memoryRegister[zMemAdr].getValue();

System.out.println("x: " + xResult + ", y: " + yResult + ", z: " + zResult);
*/

/*6.2
//pre-declared memory adresses
int xMemAdr = 0;
int yMemAdr = 0;

//pre-declared instruction points
int while_InstructionAdr = 2;
int end_InstructionAdr = 15;

//process code
MIMAInstruction[] process = {
    new MIMAInstruction("LDC", );
    new MIMAInstruction("STV", );
    new MIMAInstruction("LDC", );
    new MIMAInstruction("NOT");
    new MIMAInstruction("ADD", 0);
    new MIMAInstruction("JMN", end_InstructionAdr);
    new MIMAInstruction("LDC", 0);
    new MIMAInstruction("NOT");
    new MIMAInstruction("LDD", );
    new MIMAInstruction("AND", );
    new MIMAInstruction("STV", );
    new MIMAInstruction("LDC", 1);
    new MIMAInstruction("ADD", yMemAdr);
    new MIMAInstruction("STV", yMemAdr);
    new MIMAInstruction("JMP", while_InstructionAdr);
    new MIMAInstruction("HALT");
};

//init minimal machine
MIMA mima = new MIMA(process);

//pre-initialization of memory adresses
mima.memoryRegister[xMemAdr] = new Bit24(42);

//start minimal machine process
mima.startProcess();
*/
