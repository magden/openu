import javax.swing.*;
import java.util.Random;

public class Program
{
    public static void main(String[] args)
    {
        Boolean shouldStartOver;
        do
        {
            shouldStartOver = false;

            //Input from the user
            String inputWidth, inputHeight;
            do
            {
                inputWidth = JOptionPane.showInputDialog("Enter matrix width");
            }
            while (inputWidth == null);

            do
            {
                inputHeight = JOptionPane.showInputDialog("Enter matrix height");
            }
            while (inputHeight == null);

            int width = Integer.parseInt(inputWidth);
            int height = Integer.parseInt(inputHeight);

            //init game of life
            GameOfLife game = new GameOfLife(height, width);
            Random randomizer = new Random();

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    Boolean randomBool = randomizer.nextBoolean();
                    game.setCellState(i, j, randomBool);
                }
            }

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
                shouldStartOver = startOverResult == JOptionPane.YES_OPTION;
            }

        }
        while (shouldStartOver);
    }
}
