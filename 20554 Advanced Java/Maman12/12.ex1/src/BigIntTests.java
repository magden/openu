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
        CPrint.blue("============== isValid tests ==============\n");
        isValidTests();

        CPrint.blue("============== toString tests ==============\n");
        toStringTests();

        CPrint.blue("============== equals tests ==============\n");
        equalsTests();

        CPrint.blue("============== compareTo tests ==============\n");
        compareToTests();

        CPrint.blue("============== plus tests ==============\n");
        plusTests();

        CPrint.blue("============== minus tests ==============\n");
        minusTests();

        CPrint.blue("============== multiply tests ==============\n");
        multiplyTests();

        CPrint.blue("============== divide tests ==============\n");
        divideTests();
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

        BigInt num8 = new BigInt("-0000000000000");
        assertEquals(num8.toString(), "0", "toString test 8");
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
        plusTest("-110", "-220", "-330", "plus test 8");
        plusTest("100", "-9990", "-9890", "plus test 9");
        plusTest("-8744443433434334432211100000000000000000000000000090000", "+8744443433434334432211100000000000000000000000000090000", "0", "plus test 10");

        //RANDOM tests
        Random rnd = new Random();
        for (int i = 0; i < 10; i++)
        {
            long rnd1 = rnd.nextLong();
            long rnd2 = rnd.nextLong();
            plusTest("" + rnd1, "" + rnd2, "" + (rnd1 + rnd2), "random plus test #" + i);
        }
    }

    private static void minusTests()
    {
        //RANDOM tests
        Random rnd = new Random();
        for (int i = 0; i < 10; i++)
        {
            long rnd1 = rnd.nextLong();
            long rnd2 = rnd.nextLong();
            minusTest("" + rnd1, "" + rnd2, "" + (rnd1 - rnd2), "random minus test #" + i);
        }
    }

    private static void multiplyTests()
    {

    }

    private static void divideTests()
    {

    }

    private static void plusTest(String num1, String num2, String expectedSum, String msg)
    {
        BigInt bigNum1 = new BigInt(num1);
        BigInt bigNum2 = new BigInt(num2);
        BigInt expectedBigSum = new BigInt(expectedSum);
        assertEquals(bigNum1.plus(bigNum2).toString(), expectedBigSum.toString(), msg);
    }

    private static void minusTest(String num1, String num2, String expectedSum, String msg)
    {
        BigInt bigNum1 = new BigInt(num1);
        BigInt bigNum2 = new BigInt(num2);
        BigInt expectedBigSum = new BigInt(expectedSum);
        assertEquals(bigNum1.minus(bigNum2).toString(), expectedBigSum.toString(), msg);
    }

    private static void multiplyTest(String num1, String num2, String expectedSum, String msg)
    {
        BigInt bigNum1 = new BigInt(num1);
        BigInt bigNum2 = new BigInt(num2);
        BigInt expectedBigSum = new BigInt(expectedSum);
        assertEquals(bigNum1.multiply(bigNum2).toString(), expectedBigSum.toString(), msg);
    }

    private static void devideTest(String num1, String num2, String expectedSum, String msg)
    {
        BigInt bigNum1 = new BigInt(num1);
        BigInt bigNum2 = new BigInt(num2);
        BigInt expectedBigSum = new BigInt(expectedSum);
        assertEquals(bigNum1.divide(bigNum2).toString(), expectedBigSum.toString(), msg);
    }

    private static void equalsTests()
    {
        assertEquals(new BigInt("123456789"), new BigInt("123456789"), "equals test 1");
        assertEquals(new BigInt("-1113232131"), new BigInt("-1113232131"), "equals test 2");
        assertEquals(new BigInt("0000000000000"), new BigInt("0"), "equals test 3");
        assertEquals(new BigInt("+0000000"), new BigInt("-000"), "equals test 4");
        assertEquals(new BigInt("+0014"), new BigInt("14"), "equals test 5");
        assertNotEquals(new BigInt("1828289"), new BigInt("-1828289"), "equals test 6");
        assertNotEquals(new BigInt("+18282894"), new BigInt("-18282894"), "equals test 7");
        assertEquals(new BigInt("280012931883488490100891209129890091889280012931883488490100891209129890091889319877112231987711222800129318834884901008912091298900918893198771122"), new BigInt("280012931883488490100891209129890091889280012931883488490100891209129890091889319877112231987711222800129318834884901008912091298900918893198771122"), "equals test 8");
        assertEquals(new BigInt("   +  9999999999    "), new BigInt("9999999999"), "equals test 9");
        assertEquals(new BigInt("  - 4444  "), new BigInt("-0004444"), "equals test 10");
        assertEquals(new BigInt("-992389239289383923289893298238923982398239823323211"), new BigInt("-992389239289383923289893298238923982398239823323211"), "equals test 11");
        assertEquals(new BigInt("-00000001"), new BigInt("-1"), "equals test 12");
        assertEquals(new BigInt("4"), new BigInt("4"), "equals test 13");
        assertEquals(new BigInt("004"), new BigInt("04"), "equals test 14");
        assertEquals(new BigInt("+030"), new BigInt("30"), "equals test 15");
        assertNotEquals(new BigInt("546464"), new BigInt("646333"), "equals test 16");
        assertNotEquals(new BigInt("-646322"), new BigInt("-646333"), "equals test 17");

    }

    private static void compareToTests()
    {
        assertEquals(new BigInt("0").compareTo(new BigInt("0")), 0, "equals test 0");
        assertEquals(new BigInt("2234234324324324124141491914941491249").compareTo(new BigInt("2234234324324324124141491914941491248")), 1, "equals test 1");
        assertEquals(new BigInt("1123112113209494538543845374537543999").compareTo(new BigInt("1123112113209494538543845374537544000")), -1, "equals test 2");
        assertEquals(new BigInt("2332323232323245467654546576879654393").compareTo(new BigInt("2332323232323245467654546576879654393")), 0, "equals test 3");
        assertEquals(new BigInt("-1000").compareTo(new BigInt("-1001")), 1, "equals test 4");
        assertEquals(new BigInt("-1000").compareTo(new BigInt("-999")), -1, "equals test 5");
        assertEquals(new BigInt("-99993").compareTo(new BigInt("-99993")), 0, "equals test 6");
        assertEquals(new BigInt("0").compareTo(new BigInt("1")), -1, "equals test 7");
        assertEquals(new BigInt("-1").compareTo(new BigInt("1")), -1, "equals test 8");
        assertEquals(new BigInt("1").compareTo(new BigInt("-1")), 1, "equals test 9");
        assertEquals(new BigInt("99").compareTo(new BigInt("100")), -1, "equals test 10");
        assertEquals(new BigInt("100").compareTo(new BigInt("99")), 1, "equals test 11");
        assertEquals(new BigInt("-999").compareTo(new BigInt("-1000")), 1, "equals test 12");
        assertEquals(new BigInt("-3223323232323232").compareTo(new BigInt("0")), -1, "equals test 13");
        assertEquals(new BigInt("0").compareTo(new BigInt("3232323233232")), -1, "equals test 14");
        assertEquals(new BigInt("9999").compareTo(new BigInt("99999")), -1, "equals test 15");
        assertEquals(new BigInt("09999").compareTo(new BigInt("99999")), -1, "equals test 16");
        assertEquals(new BigInt("000000").compareTo(new BigInt("000")), 0, "equals test 17");
        assertEquals(new BigInt("-0").compareTo(new BigInt("+0")), 0, "equals test 18");
    }

    public static void assertEquals(Object o1, Object o2, String desc)
    {
        if (o1.equals(o2))
        {
            CPrint.green("[PASS] ");
            System.out.println(desc + "\t(" + o1 + " is equal to " + o2 + ")");
        }
        else
        {
            CPrint.red("[FAIL] ");
            CPrint.red(desc + "\t(" + o1 + " is not equal to " + o2 + ")\n");
        }
    }

    public static void assertNotEquals(Object o1, Object o2, String desc)
    {
        if (o1.equals(o2))
        {
            CPrint.red("[FAIL] ");
            CPrint.red(desc + "\t(" + o1 + " shouldn't be equal to " + o2 + ")\n");
        }
        else
        {
            CPrint.green("[PASS] ");
            System.out.println(desc + "\t(" + o1 + " is not equal to " + o2 + ")");
        }
    }

    public static void assertTrue(Boolean condition, String desc)
    {
        assertEquals(condition, true, desc);
    }
}
