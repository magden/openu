import java.util.ArrayList;

/**
 * Created by Stas on 13/03/2015.
 */
public class BigInt implements Comparable<BigInt>
{
    private ArrayList<Integer> digitArray;

    public BigInt(String number)
    {
        digitArray = new ArrayList<Integer>();
        if (!isValidNumber(number))
        {
            throw new IllegalArgumentException("String '" + number + "' is not a valid number");
        }
    }

    public static boolean isValidNumber(String str)
    {
        return false;
    }

    public BigInt plus(BigInt num)
    {
        return null;
    }

    public BigInt minus(BigInt num)
    {
        return null;
    }

    public BigInt multiply(BigInt operand)
    {
        return null;
    }

    public BigInt divide(BigInt operand)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }

    @Override
    public int compareTo(BigInt bigInt)
    {
        return 0;
    }
}
