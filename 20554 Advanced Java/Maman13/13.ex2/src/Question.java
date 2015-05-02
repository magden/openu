import java.util.ArrayList;

/**
 * Created by Stas on 02/05/2015 10:35.
 */

public class Question
{
    private String questionText;
    private ArrayList<Answer> answers;

    public Question(String text, ArrayList<Answer> answers)
    {
        this.questionText = text;
        this.answers = answers;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public ArrayList<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers)
    {
        this.answers = answers;
    }
}
