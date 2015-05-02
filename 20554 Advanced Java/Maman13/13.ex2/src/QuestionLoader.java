import java.io.*;
import java.util.*;

/**
 * Created by Stas on 02/05/2015 11:20.
 */

public class QuestionLoader
{
    private static final int ANSWERS_PER_QUESTION = 4;

    public static ArrayList<Question> readQuestionsFromFile(String fileName) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(fileName));
        ArrayList<Question> questions = new ArrayList<Question>();
        while (file.hasNext())
        {
            questions.add(readQuestion(file));
        }
        return questions;
    }

    private static Question readQuestion(Scanner file)
    {
        if (file.hasNext())
        {
            String questionText = file.nextLine();
            ArrayList<Answer> answers = new ArrayList<Answer>();
            int i = 0;
            while (file.hasNext() && i < ANSWERS_PER_QUESTION)
            {
                String answerText = file.nextLine();
                boolean isCorrect = (i == 0);
                answers.add(new Answer(answerText, isCorrect));
                i++;
            }

            return new Question(questionText, answers);
        }

        return null;
    }
}
