import java.util.*;

/**
 * Created by Stas on 02/05/2015 10:44.
 */

public class TriviaGame
{
    private ArrayList<Question> questions;
    private ListIterator<Question> questionsIt;
    private int score;

    public TriviaGame(ArrayList<Question> questions)
    {
        this.questions = questions;
        questionsIt = questions.listIterator();
    }

    public Question nextQuestion()
    {
        if (questions.size() == 0)
        {
            return null;
        }

        if (!questionsIt.hasNext())
        {
            questionsIt = questions.listIterator();
        }
        return questionsIt.next();
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int amount)
    {
        this.score += amount;
    }
}
