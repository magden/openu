import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Stas on 13/03/2015.
 */
public class BigInt implements Comparable<BigInt>
{
    private ArrayList<Byte> digitArray;
    private boolean isNegative; //false for zeros

    public BigInt(String number)
    {
        //check validity of the input string
        if (!isValidNumber(number))
        {
            throw new IllegalArgumentException("String '" + number + "' is not a valid number");
        }

        //determine sign
        number = number.trim();
        if (number.charAt(0) == '-')
        {
            isNegative = true;
            number = number.substring(1); //trim the first '-'
        }
        else if (number.charAt(0) == '+')
        {
            isNegative = false;
            number = number.substring(1); //trim the first '+'
        }
        number = number.trim();

        //build the number
        digitArray = new ArrayList<Byte>();
        char[] digits = number.toCharArray();

        for (int i = digits.length - 1; i >= 0; i--)
        {
            byte digit = (byte) (digits[i] - '0');
            digitArray.add(digit);
        }
    }

    private boolean isValidNumber(String str)
    {
        return str.matches("^\\s*[-+]?\\s*[0-9]+\\s*$");
    }

    public BigInt plus(BigInt num)
    {
        BigInt result = new BigInt("0");
        return result;
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
        String result = "";
        for (byte digit : digitArray)
        {
            result = digit + result;
        }

        if (isNegative)
        {
            result = "-" + result;
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof BigInt)
        {
            BigInt otherNum = (BigInt) o;
            if (otherNum.isNegative != this.isNegative)
            {
                return false;
            }


        }
        return false;
    }

    @Override
    public int compareTo(BigInt bigInt)
    {
        return 0;
    }
}
