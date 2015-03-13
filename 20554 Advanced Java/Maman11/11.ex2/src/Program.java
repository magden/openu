import javax.swing.*;
import java.util.Random;

public class Program
{
    public static void main(String[] args)
    {
        boolean shouldStartOver;
        do
        {
            shouldStartOver = false;

            //Input from the user
            int width = getIntFromUser("Enter matrix width");
            int height = getIntFromUser("Enter matrix height");

            //init game of life
            GameOfLife game = new GameOfLife(height, width);
            randomizeCells(game, height, width);

            //show and handle results
            int dialogResult = JOptionPane.showConfirmDialog(null, game);

            while (dialogResult == JOptionPane.YES_OPTION)
            {
                game.nextGeneration();
                dialogResult = JOptionPane.showConfirmDialog(null, game);
            }

            if (dialogResult == JOptionPane.CANCEL_OPTION)
            {
                int startOverResult = JOptionPane.showConfirmDialog(null, "Would you like to start over?", null, JOptionPane.YES_NO_OPTION);
                shouldStartOver = (startOverResult == JOptionPane.YES_OPTION);
            }

        }
        while (shouldStartOver);
    }

    private static void randomizeCells(GameOfLife game, int height, int width)
    {
        Random randomizer = new Random();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                boolean randomBool = randomizer.nextBoolean();
                game.setCellState(i, j, randomBool);
            }
        }
    }

    private static int getIntFromUser(String message)
    {
        String input;
        do
        {
            input = JOptionPane.showInputDialog(message);
        }
        while (input == null);

        return Integer.parseInt(input);
    }
}
