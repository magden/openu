import java.util.ArrayList;

/**
 * Created by Stas on 13/03/2015.
 */
public class GuessingGame
{
    private static final int NUMBER_OF_DIGITS = 4;
    private static final int NOT_FOUND = -1;

    private GuessState[][] gameState = new GuessState[NUMBER_OF_DIGITS][10];
    private int[] lastGuess;
    private int[] realAnswer = new int[NUMBER_OF_DIGITS];
    private int numberOfGuesses;
    private boolean isConflict;

    public GuessingGame()
    {
        newGame();
    }

    public void newGame()
    {
        numberOfGuesses = 0;
        isConflict = false;
        for (int i = 0; i < gameState.length; i++)
        {
            for (int j = 0; j < gameState[i].length; j++)
            {
                gameState[i][j] = GuessState.UNKNOWN;
            }
        }

        for (int i = 0; i < realAnswer.length; i++)
        {
            realAnswer[i] = NOT_FOUND;
        }
    }

    public static boolean isValidHint(String hint)
    {
        if (hint == null || hint.length() != NUMBER_OF_DIGITS)
        {
            return false;
        }

        for (int i = 0; i < NUMBER_OF_DIGITS; i++)
        {
            char c = hint.charAt(i);
            if (c != 'B' && c != 'H' && c != 'X')
            {
                return false;
            }
        }

        return true;
    }

    public int[] guess()
    {
        //CONFLICT MODE: user has tricked us with incorrect answers
        if (isConflict)
        {
            return new int[]{0, 0, 0, 0};
        }

        numberOfGuesses++;

        ArrayList<Integer> guesses = new ArrayList<Integer>();
        for (int i = 0; i < NUMBER_OF_DIGITS; i++)
        {
            if (realAnswer[i] != NOT_FOUND)
            {
                guesses.add(realAnswer[i]);
            }
            else
            {
                Integer guessDigit = getPossibleDigit(i, guesses);

                if (guessDigit == NOT_FOUND)
                {
                    //CONFLICT MODE: user has tricked us with incorrect answers
                    isConflict = true;
                    return new int[]{0, 0, 0, 0};
                }
                guesses.add(guessDigit);
            }
        }

        //convert list to primitive array and return it
        int[] guess = new int[NUMBER_OF_DIGITS];
        for (int i = 0; i < guesses.size(); i++)
        {
            guess[i] = guesses.get(i);
        }

        lastGuess = guess;
        return guess.clone();
    }

    public int getNumberOfGuesses()
    {
        return numberOfGuesses;
    }

    public String getAnswer()
    {
        if (!isFinishGuessing())
        {
            return "N/A";
        }

        String answer = "";
        for (int digit : realAnswer)
        {
            answer += digit;
        }

        return answer;
    }

    public boolean isFinishGuessing()
    {
        for (int digit : realAnswer)
        {
            if (digit == NOT_FOUND)
            {
                return false;
            }
        }

        return true;
    }

    private int getPossibleDigit(int position, ArrayList<Integer> guesses)
    {
        for (int i = 0; i < 10; i++)
        {
            if (gameState[position][i] == GuessState.UNKNOWN && !guesses.contains(i))
            {
                return i;
            }
        }

        return NOT_FOUND; // if we get here, the use is fooled us with incorrect hints
    }

    public void applyHint(String hint)
    {
        //invalid hint or conflict mode should do nothing
        if (isConflict || !isValidHint(hint))
        {
            return;
        }

        char[] hintArray = hint.toUpperCase().toCharArray();
        for (int i = 0; i < hintArray.length; i++)
        {
            switch (hintArray[i])
            {
                case 'B':
                    setBingo(i, lastGuess[i]);
                    break;

                case 'H':
                    setHit(i, lastGuess[i]);
                    break;

                case 'X':
                    setMiss(lastGuess[i]);
                    break;

                default:
                    break;
            }
        }
    }

    private void setBingo(int position, int digit)
    {
        realAnswer[position] = digit;
        for (int i = 0; i < 10; i++)
        {
            if (i == digit)
            {
                gameState[position][i] = GuessState.BINGO;
            }
            else
            {
                gameState[position][i] = GuessState.NOT_POSSIBLE;
            }
        }

        for (int i = 0; i < NUMBER_OF_DIGITS; i++)
        {
            if (gameState[i][digit] != GuessState.BINGO)
            {
                gameState[i][digit] = GuessState.NOT_POSSIBLE;
            }
        }
    }

    private void setHit(int position, int digit)
    {
        gameState[position][digit] = GuessState.NOT_POSSIBLE;
    }

    private void setMiss(int digit)
    {
        for (int i = 0; i < NUMBER_OF_DIGITS; i++)
        {
            gameState[i][digit] = GuessState.NOT_POSSIBLE;
        }
    }

    public boolean isConflict()
    {
        return isConflict;
    }
}
