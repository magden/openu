import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
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

        //remove leading zeros
        number = removeLeadingZeros(number);

        //correction for 0
        if (number.isEmpty())
        {
            number = "0";
            isNegative = false;
        }

        //build the number
        digitArray = new ArrayList<Byte>();
        char[] digits = number.toCharArray();

        for (int i = digits.length - 1; i >= 0; i--)
        {
            byte digit = (byte) (digits[i] - '0');
            digitArray.add(digit);
        }
    }

    private String removeLeadingZeros(String number)
    {
        int zeroCount = 0;
        while (zeroCount < number.length() && number.charAt(zeroCount) == '0')
        {
            zeroCount++;
        }

        if (zeroCount > 0)
        {
            number = number.substring(zeroCount);
        }
        return number;
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
            return this.compareTo(otherNum) == 0;

        }
        return false;
    }

    @Override
    public int compareTo(BigInt otherNum)
    {
        if (otherNum.isNegative && !this.isNegative)
        {
            return 1;
        }
        else if (!otherNum.isNegative && this.isNegative)
        {
            return -1;
        }

        boolean areNegative = otherNum.isNegative && this.isNegative;
        int thisLength = this.digitArray.size();
        int otherLength = otherNum.digitArray.size();

        if (thisLength < otherLength)
        {
            return areNegative ? 1 : -1;
        }
        else if (thisLength > otherLength)
        {
            return areNegative ? -1 : 1;
        }

        ListIterator<Byte> thisIt = this.digitArray.listIterator(thisLength);
        ListIterator<Byte> otherIt = otherNum.digitArray.listIterator(otherLength);

        while (thisIt.hasPrevious() && otherIt.hasPrevious())
        {
            Byte thisDigit = thisIt.previous();
            Byte otherDigit = otherIt.previous();
            if (thisDigit < otherDigit)
            {
                return areNegative ? 1 : -1;
            }
            else if (thisDigit > otherDigit)
            {
                return areNegative ? -1 : 1;
            }
        }

        return 0;
    }
}
