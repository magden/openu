import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stas on 13/03/2015.
 */
public class GuessingGame
{
    private final int NUMBER_OF_DIGITS = 4;
    private final int NOT_FOUND = -1;

    private GuessState[][] gameState = new GuessState[NUMBER_OF_DIGITS][10];
    private int[] lastGuess;
    private int[] realAnswer = new int[NUMBER_OF_DIGITS];
    private int numberOfGuesses;

    public GuessingGame()
    {
        newGame();
    }

    public void newGame()
    {
        numberOfGuesses = 0;
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

    public Boolean isValidHint(String hint)
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

    public Boolean isFinishGuessing()
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
        int[] randomDigits = createRandomDigitArray();

        for (int i : randomDigits)
        {
            if (gameState[position][i] == GuessState.UNKNOWN && !guesses.contains(i))
            {
                return i;
            }
        }

        return -1; // if we get here, the use is fooled us with incorrect hints
    }

    public int[] createRandomDigitArray()
    {
        int[] randomArray = new int[10];
        for (int i = 0; i < 10; i++)
        {
            randomArray[i] = NOT_FOUND;
        }

        for (int i = 0; i < 10; i++)
        {
            int random;
            do
            {
                random = new Random().nextInt(10);
            }
            while (randomArray[random] != NOT_FOUND);
            randomArray[random] = i;
        }

        return randomArray;
    }

    public void applyHint(String hint)
    {
        //invalid hint
        if (!isValidHint(hint))
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
}
