/**
 * Created by Stas on 02/05/2015 10:36.
 */

public class Answer
{
    private String answerText;
    private boolean isCorrect;

    public Answer(String text, boolean isCorrect)
    {
        this.answerText = text;
        this.isCorrect = isCorrect;
    }

    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
    }

    public boolean isCorrect()
    {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect)
    {
        this.isCorrect = isCorrect;
    }
}
