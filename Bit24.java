//Holds only values from -(2^24) and 2^24-1
//May be further customized to extend a future superclass "CustomBit"
public class Bit24 {
    private static final int MAX_VALUE = (int)(Math.pow(2, 24) - 1);
    private static final int MIN_VALUE = (int)(-Math.pow(2, 24) - 1);
    private int value;

    Bit24() { setValue(0); }
    Bit24(int x) { setValue(x); }

    public void setValue(int x) {
        if (x > MAX_VALUE)
            value = MAX_VALUE;
        else if (x < MIN_VALUE)
            value = MIN_VALUE;
        else
            value = x;
    }

    public int getValue() { return value; }
}
