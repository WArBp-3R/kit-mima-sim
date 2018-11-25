public class Main {
    public static void main(String[] args) {
        //Example
        //pre-declared memory adresses
        int len = 0;
        int[] list = {1, 2, 3, 4, 5};
        int cnt = 6;
        int ref = 7;
        int sum = 8;

        //pre-declared instruction points
        int start = 0;
        int next = 5;
        int done = 18;

        //process code
        MIMAInstruction[] process = {
            new MIMAInstruction("LDC", 0), //start
            new MIMAInstruction("STV", sum),
            new MIMAInstruction("STV", cnt),
            new MIMAInstruction("LDC", list[0]),
            new MIMAInstruction("STV", ref),
            new MIMAInstruction("LDIV", ref), //next
            new MIMAInstruction("ADD", sum),
            new MIMAInstruction("STV", sum),
            new MIMAInstruction("LDC", 1),
            new MIMAInstruction("ADD", cnt),
            new MIMAInstruction("STV", cnt),
            new MIMAInstruction("LDC", 1),
            new MIMAInstruction("ADD", ref),
            new MIMAInstruction("STV", ref),
            new MIMAInstruction("LDV", cnt),
            new MIMAInstruction("EQL", len),
            new MIMAInstruction("JMN", done),
            new MIMAInstruction("JMP", next),
            new MIMAInstruction("HALT") //done
        };

        //init minimal machine
        MIMA mima = new MIMA(process);

        //pre-initialization of memory adresses
        mima.setMemValAt(len, 5);
        mima.setMemValAt(list[0], 11);
        mima.setMemValAt(list[1], 22);
        mima.setMemValAt(list[2], 33);
        mima.setMemValAt(list[3], 44);
        mima.setMemValAt(list[4], 55);

        //start minimal machine process
        mima.startProcess();

        //reading results from memory register
        int sumResult = mima.getMemValAt(sum);

        System.out.println("sum: " + sumResult);

        //can reset memory and instruction of MIMA
        mima.resetMemory();
        mima.resetInstructions();
    }
}
