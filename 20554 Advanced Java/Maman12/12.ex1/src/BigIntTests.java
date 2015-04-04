import com.sun.source.tree.AssertTree;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Random;

/**
 * Created by Stas on 04/04/2015.
 */
public class BigIntTests
{
    public static void allTests()
    {
        isValidTests();
        toStringTests();
        plusTests();
    }

    private static void isValidTests()
    {
        isValidTest("0000000000333", true);
        isValidTest("not a number", false);
        isValidTest("9239392392x", false);
        isValidTest("", false);
        isValidTest("323323232", true);
        isValidTest("-333000333", true);
        isValidTest("+9", true);
        isValidTest("+", false);
        isValidTest("-", false);
        isValidTest("+-3", false);
        isValidTest("4554-", false);
        isValidTest("     455  ", true);
        isValidTest(" -    343434430  ", true);
        isValidTest("+0", true);
        isValidTest("343443433443843894389027094720934709237409237409237490273940374020", true);
        isValidTest("-3434434334438443434409472093470923740923997409237490273940374020", true);
        isValidTest("3.149509", false);
        isValidTest("343434343,43434,3443.", false);

    }

    private static void isValidTest(String input, Boolean shouldBeValid)
    {
        try
        {
            BigInt num1 = new BigInt(input);

            if (shouldBeValid)
            {
                assertTrue(true, "'" + input + "' is a valid number");
            }
            else
            {
                assertTrue(false, "'" + input + "' Should be an invalid number but is a valid instead");
            }
        }
        catch (IllegalArgumentException ex)
        {
            if (shouldBeValid)
            {
                assertTrue(false, "'" + input + "' Should be a valid number");
            }
            else
            {
                assertTrue(true, "'" + input + "' is an invalid number");
            }
        }
        catch (Exception ex)
        {
            assertTrue(false, "Got exception while create BigInt: " + ex.getMessage());
        }
    }


    private static void toStringTests()
    {
        BigInt num1 = new BigInt("1234567890667");
        assertEquals(num1.toString(), "1234567890667", "toString test 1");

        BigInt num2 = new BigInt("-987");
        assertEquals(num2.toString(), "-987", "toString test 2");

        BigInt num3 = new BigInt("+9966637383");
        assertEquals(num3.toString(), "9966637383", "toString test 3");

        BigInt num4 = new BigInt("0000000000000");
        assertEquals(num4.toString(), "0", "toString test 4");

        BigInt num5 = new BigInt("-0013");
        assertEquals(num5.toString(), "-13", "toString test 5");

        BigInt num6 = new BigInt("   449980   ");
        assertEquals(num6.toString(), "449980", "toString test 6");

        BigInt num7 = new BigInt("-929283998723929283998723879234784932894239824328943723767842358461971000099999988879234784932894239824328943723767842358461971000099999988");
        assertEquals(num7.toString(), "-929283998723929283998723879234784932894239824328943723767842358461971000099999988879234784932894239824328943723767842358461971000099999988", "toString test 7");
    }

    private static void plusTests()
    {
        plusTest("123", "321", "444", "plus test 1");
        plusTest("999", "22", "1021", "plus test 2");
        plusTest("9999999913213123131312312312319", "10001", "9999999913213123131312312322320", "plus test 3");
        plusTest("0", "0", "0", "plus test 4");
        plusTest("0", "55", "55", "plus test 5");
        plusTest("100000000100000000100000000", "100000000100000000100000000", "200000000200000000200000000", "plus test 6");
        plusTest("198889", "-999", "197890", "plus test 7");
        plusTest("-100", "-220", "-330", "plus test 8");
        plusTest("100", "-9990", "-0", "plus test 9");
        plusTest("0", "0", "0", "plus test 10");

        //RANDOM tests
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++)
        {
            long rnd1 = rnd.nextLong();
            long rnd2 = rnd.nextLong();
            plusTest("" + rnd1, "" + rnd2, "" + (rnd1 + rnd2), "random plus test #" + i);
        }
    }

    private static void plusTest(String num1, String num2, String expectedSum, String msg)
    {
        BigInt bigNum1 = new BigInt(num1);
        BigInt bigNum2 = new BigInt(num2);
        BigInt expectedBigSum = new BigInt(expectedSum);
        assertEquals(bigNum1.plus(bigNum2).toString(), expectedBigSum.toString(), msg);
    }

    public static void assertEquals(Object o1, Object o2, String desc)
    {
        if (o1.equals(o2))
        {
            CPrint.green("[PASS] ");
            System.out.println(desc + "\t(" + o1 + " is equals to " + o2 + ")");
        }
        else
        {
            CPrint.red("[FAIL] ");
            CPrint.red(desc + "\t(" + o1 + " is not equals to " + o2 + ")\n");
        }
    }

    public static void assertTrue(Boolean condition, String desc)
    {
        assertEquals(condition, true, desc);
    }
}
