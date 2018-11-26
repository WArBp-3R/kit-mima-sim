# kit-mima-sim
_Created by Walter Alexander Böttcher (23.11.2018)_

#### Simulates the KIT minimal machine

You can find the example (example program from GBI sheet Nr.10 on processors (WS18/19)) on how to use the MIMA-Simulator by editing main source code in Main.java (look further down):

### Dowloading/using for the first time
Download the repository in a ZIP-file (or however you want)

In the folder, run these commands:

    javac Main.java
    java Main

If everything works, you should now see this output:

    sum: 165

Now you will only need to change the Main.java file for your own tests, then compile `javac Main.java` and run `java Main` it.

### MIMA command set
**LDC** *const*:<br/>
&nbsp; &nbsp; loads a constant value into the accumulator<br/>
**LDV** *adress*:<br/>
&nbsp; &nbsp; loads a value from memory at given adress into the accumulator<br/>
**STV** *adress*:<br/>
&nbsp; &nbsp; stores the value from the accumulator in memory at given adress<br/>
**LDIV** *adress*:<br/>
&nbsp; &nbsp; loads a value from memory at given adress, <br/>
&nbsp; &nbsp; then uses this value as adress to load from memory into the accumulator<br/>
**STIV** *adress*:<br/>
&nbsp; &nbsp; loads a value from memory at given adress, <br/>
&nbsp; &nbsp; then uses this value as adress to store the accumulator value in memory<br/>
**ADD** *adress*:<br/>
&nbsp; &nbsp; adds the value from memory at given adress to the accumulator<br/>
**AND** *adress*:<br/>
&nbsp; &nbsp; performs the AND-operation bitwise on accumulator and value from memory at given adress<br/>
**OR** *adress*:<br/>
&nbsp; &nbsp; performs the OR-operation bitwise on accumulator and value from memory at given adress<br/>
**XOR** *adress*:<br/>
&nbsp; &nbsp; performs the XOR-operation bitwise on accumulator and value from memory at given adress<br/>
**RAR**:<br/>
&nbsp; &nbsp; reverses the bits in the accumulator (e.g. 011011100101110111100010 -> 010001111011101001110110)<br/>
**NOT**:<br/>
&nbsp; &nbsp; inverts the bits (0->1, 1->0) in the accumulator (effectively the negative value of the accumulator minus 1)<br/>
**EQL** *adress*:<br/>
&nbsp; &nbsp; sets all bits to 1, if the accumulator value has same value from memory at given adress, <br/>
&nbsp; &nbsp; otherwise sets all bits to 0<br/>
**JMP** *adress*:<br/>
&nbsp; &nbsp; jumps to the instruction at given adress<br/>
**JMN** *adress*:<br/>
&nbsp; &nbsp; executes `JMP adress` if first bit in accumulator is 1<br/>
**HALT**:<br/>
&nbsp; &nbsp; halts the program<br/>

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
