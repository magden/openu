import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Stas on 13/03/2015.
 */
public class BigInt implements Comparable<BigInt>
{
    private ArrayList<Integer> digitArray;
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
        digitArray = new ArrayList<Integer>();
        char[] digits = number.toCharArray();

        boolean ignoreZeros = true;
        for (int i = 0; i < digits.length; i++)
        {
            int digit = digits[i] - '0';

            if (digit == 0 && ignoreZeros)
            {
                continue;
            }

            digitArray.add(digit);
            ignoreZeros = false;
        }

        //correction for zero
        if (digitArray.size() == 0)
        {
            digitArray.add(0);
            isNegative = false;
        }
    }

    private boolean isValidNumber(String str)
    {
        return str.matches("^\\s*[-+]?\\s*[0-9]+\\s*$");
    }

    public BigInt plus(BigInt otherNum)
    {
        String result = "";

        BigInt bigger, smaller;
        int compareResult = this.compareTo(otherNum);

        if (compareResult != 0)
        {
            if (compareResult == 1)
            {
                bigger = otherNum;
                smaller = this;
            }
            else if (compareResult == -1)
            {
                bigger = this;
                smaller = otherNum;
            }
            else
            {
                bigger = smaller = null;
            }

            if (bigger.isNegative && !smaller.isNegative)
            {
                BigInt minusBigger = new BigInt(bigger.toString());
                BigInt minusSmaller = new BigInt(smaller.toString());
                bigger.isNegative = !bigger.isNegative;
                smaller.isNegative = !smaller.isNegative;
                BigInt answer = minusBigger.plus(minusSmaller);
                answer.isNegative = !answer.isNegative;
                return answer;
            }
        }

        //Going backwards!
        ListIterator<Integer> thisIt = this.digitArray.listIterator(this.digitArray.size());
        ListIterator<Integer> otherIt = otherNum.digitArray.listIterator(otherNum.digitArray.size());

        Boolean bothNegative = this.isNegative && otherNum.isNegative;

        int extra = 0;
        while (thisIt.hasPrevious() || otherIt.hasPrevious() || extra != 0)
        {
            int thisDigit = thisIt.hasPrevious() ? thisIt.previous() : 0;
            int otherDigit = otherIt.hasPrevious() ? otherIt.previous() : 0;

            //negative multiplier
            if (this.isNegative && !bothNegative)
            {
                thisDigit *= (-1);
            }
            if (otherNum.isNegative && !bothNegative)
            {
                otherDigit *= (-1);
            }

            int resultDigit = thisDigit + otherDigit + extra;

            extra = 0;

            if (resultDigit >= 10)
            {
                extra = resultDigit / 10;
                resultDigit -= 10;
            }
            else if (resultDigit < 0)
            {
                resultDigit += 10;
                extra = -1;
            }

            result = resultDigit + result;
        }

        if (bothNegative)
        {
            result = "-" + result;
        }
        return new BigInt(result);
    }

    public BigInt minus(BigInt otherNum)
    {
        //Minus is just plus when the other operand has a different sign
        BigInt numWithDifferentSign = new BigInt(otherNum.toString());
        numWithDifferentSign.isNegative = !numWithDifferentSign.isNegative;
        return this.plus(numWithDifferentSign);
    }

    public BigInt multiply(BigInt otherNum)
    {
        return null;
    }

    public BigInt divide(BigInt otherNum)
    {
        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (int digit : digitArray)
        {
            result.append(digit);
        }

        if (isNegative)
        {
            result.insert(0, "-");
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

        ListIterator<Integer> thisIt = this.digitArray.listIterator();
        ListIterator<Integer> otherIt = otherNum.digitArray.listIterator();

        while (thisIt.hasNext() && otherIt.hasNext())
        {
            Integer thisDigit = thisIt.next();
            Integer otherDigit = otherIt.next();
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
