import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stas on 13/03/2015.
 */
public class GuessingGame
{
    private final int NUMBER_OF_DIGITS = 4;
    private GuessState[][] gameState = new GuessState[NUMBER_OF_DIGITS][10];
    private int[] lastGuess;

    public GuessingGame()
    {
        for (int i = 0; i < gameState.length; i++)
        {
            for (int j = 0; j < gameState[i].length; j++)
            {
                gameState[i][j] = GuessState.UNKNOWN;
            }
        }
    }

    public Boolean isValidHint(String hint)
    {
        if (hint.length() > NUMBER_OF_DIGITS)
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

    public int guess()
    {
        ArrayList<Integer> guesses = new ArrayList<Integer>();
        for (int i = 0; i < NUMBER_OF_DIGITS; i++)
        {
            Integer guessDigit = getPossibleDigit(i, guesses);
            guesses.add(guessDigit);
        }

        int guess = 0;
        for (int d : guesses)
        {
            guess = guess * 10 + d;
        }
        return guess;
    }

    private int getPossibleDigit(int position, ArrayList<Integer> guesses)
    {

        for (int i = 0; i < gameState[position].length; i++)
        {
            if (gameState[position][i] == GuessState.UNKNOWN && !guesses.contains(i))
            {
                return i;
            }
        }

        return -1; //we shouldn't get here
    }


    public void applyHint(String hint)
    {
        //invalid hint
        if (isValidHint(hint))
        {
            return;
        }

        char[] hintArray = hint.toUpperCase().toCharArray();
        for (int i = 0; i <= hintArray.length; i++)
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
