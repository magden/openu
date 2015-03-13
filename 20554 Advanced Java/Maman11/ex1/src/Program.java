import javax.swing.*;

public class Program
{
    public static void main(String[] args)
    {
        GuessingGame game = new GuessingGame();
        Boolean shouldPlayAgain;
        do
        {
            game.newGame();
            while (!game.isFinishGuessing())
            {
                //Computer guess
                int[] guess = game.guess();
                String stringGuess = "";

                for (int g : guess)
                {
                    stringGuess += g;
                }

                //Get input from user:
                String hint = JOptionPane.showInputDialog("\nComputer's guess: " + stringGuess + "\nUser hint:");
                if (hint != null)
                {
                    hint = hint.toUpperCase();
                }
                while (!game.isValidHint(hint))
                {
                    String incorrectMsg = "Invalid hint. Please provide 4 digit string containing only: 'X', 'H' or 'B':";
                    hint = JOptionPane.showInputDialog("\nComputer's guess: " + stringGuess + "\n" + incorrectMsg);
                }

                game.applyHint(hint);
            }


            String message = "Found it! Your number is " + game.getAnswer() + "\nIt took me " + game.getNumberOfGuesses() + " guesses\n\nWould you like to have another game?";
            int dialogResult = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
            shouldPlayAgain = dialogResult == JOptionPane.YES_OPTION;
        }
        while (shouldPlayAgain);
    }
}
