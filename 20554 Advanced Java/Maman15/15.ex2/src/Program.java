import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stas on 26/06/2015 22:27.
 */

public class Program
{
    public static void main(String [] args)
    {

        //Input from the user
        int width = getIntFromUser("Enter matrix width");
        int height = getIntFromUser("Enter matrix height");

        //init ui
        JFrame window = new JFrame("MuliThreading");
        window.setSize(550, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        GridPanel grid = new GridPanel(height, width);
        window.add(grid, BorderLayout.CENTER);

        ControlPanel control = new ControlPanel(grid);
        window.add(control, BorderLayout.NORTH);

        window.setVisible(true);
    }

    private static int getIntFromUser(String message)
    {
        String input;
        do
        {
            input = JOptionPane.showInputDialog(message);
        }
        while (input == null || input.length() == 0);

        return Integer.parseInt(input);
    }
}
