import javax.swing.*;

/**
 * Created by Stas on 13/03/2015 19:33 19:33.
 */
public class Program
{
    public static void main(String args[])
    {
        //input
        BigInt num1 = getBigIntFromUser("Enter first number: ");
        BigInt num2 = getBigIntFromUser("Enter second number: ");

        //plus
        BigInt result = num1.plus(num2);
        JOptionPane.showMessageDialog(null, num1 + "\n+\n" + num2 + "\n=\n" + result);

        //minus
        result = num1.minus(num2);
        JOptionPane.showMessageDialog(null, num1 + "\n-\n" + num2 + "\n=\n" + result);

        //multiply
        result = num1.multiply(num2);
        JOptionPane.showMessageDialog(null, num1 + "\n*\n" + num2 + "\n=\n" + result);

        //divide
        result = num1.divide(num2);
        JOptionPane.showMessageDialog(null, num1 + "\n/\n" + num2 + "\n=\n" + result);

        //compare
        int compareResult = num1.compareTo(num2);
        if (compareResult == 0)
        {
            JOptionPane.showMessageDialog(null, num1 + "\nis equal to\n" + num2);
        }
        else if (compareResult == -1)
        {
            JOptionPane.showMessageDialog(null, num1 + "\nsmaller than\n" + num2);
        }
        else
        {
            JOptionPane.showMessageDialog(null, num1 + "\nbigger than\n" + num2);
        }

    }

    private static BigInt getBigIntFromUser(String message)
    {
        BigInt bigIntFromUser = null;
        String input = JOptionPane.showInputDialog(message);

        do
        {
            try
            {
                bigIntFromUser = new BigInt(input);
            }
            catch (IllegalArgumentException ex)
            {
                input = JOptionPane.showInputDialog("Illegal number format.\n" + message);
            }
        }
        while (bigIntFromUser == null);

        return bigIntFromUser;
    }
}
