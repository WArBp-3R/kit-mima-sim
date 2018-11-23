# kit-mima-sim
Simulates the KIT minimal machine

Created by Walter Alexander Böttcher (23.11.2018)

You can find an example (example program from GBI sheet Nr.10 on proccessors (WS18/19)) on how to use the MIMA-Simulator by editing main source code in Main.java:

## Main.java

```
public class Main {
    public static void main(String[] args) {
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
        mima.setMemoryValueAt(len, 5);
        mima.setMemoryValueAt(list[0], 11);
        mima.setMemoryValueAt(list[1], 22);
        mima.setMemoryValueAt(list[2], 33);
        mima.setMemoryValueAt(list[3], 44);
        mima.setMemoryValueAt(list[4], 55);

        //start minimal machine process
        mima.startProcess();

        //reading results from memory register
        int sumResult = mima.getMemoryValueAt(sum);

        System.out.println("sum: " + sumResult);

        //can reset the minimal machine
        mima.reset();
    }
}

```
