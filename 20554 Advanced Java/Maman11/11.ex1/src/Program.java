import javax.swing.*;

public class Program
{
    public static void main(String[] args)
    {
        GuessingGame game = new GuessingGame();
        boolean shouldPlayAgain;
        do
        {
            game.newGame();
            while (!game.isFinishGuessing() && !game.isConflict())
            {
                //Computer guess
                int[] guess = game.guess();
                String stringGuess = getStringFromIntArray(guess);

                if (!game.isConflict())
                {
                    //User apply hint
                    String hint = getHintFromUser(stringGuess);
                    game.applyHint(hint);
                }
            }

            if (game.isFinishGuessing())
            {
                shouldPlayAgain = showFinishDialog(game);
            }
            else
            {
                shouldPlayAgain = showConflictDialog();
            }

        }
        while (shouldPlayAgain);
    }

    private static boolean showConflictDialog()
    {
        String message = "You tricked me! There is no way i can guess the correct answer\nWould you like to have another game?";
        int dialogResult = JOptionPane.showConfirmDialog(null, message, "Conflict Detected!", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private static boolean showFinishDialog(GuessingGame game)
    {
        String message = "Found it! Your number is " + game.getAnswer() + "\nIt took me " + game.getNumberOfGuesses() + " guesses\n\nWould you like to have another game?";
        int dialogResult = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private static String getHintFromUser(String computerGuess)
    {
        String hint = JOptionPane.showInputDialog("\nComputer's guess: " + computerGuess + "\nUser hint:");
        if (hint != null)
        {
            hint = hint.toUpperCase();
        }
        while (!GuessingGame.isValidHint(hint))
        {
            String incorrectMsg = "Invalid hint. Please provide 4 digit string containing only: 'X', 'H' or 'B':";
            hint = JOptionPane.showInputDialog("\nComputer's guess: " + computerGuess + "\n" + incorrectMsg);
        }

        return hint;
    }

    private static String getStringFromIntArray(int[] arr)
    {
        String str = "";

        for (int x : arr)
        {
            str += x;
        }

        return str;
    }
}
