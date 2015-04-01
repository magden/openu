import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stas on 01/04/2015.
 */
public class Program
{
    public static void main(String args[])
    {
        ArrayList<MyShape> shapes = createShapes();
    }

    private static ArrayList<MyShape> createShapes()
    {
        ArrayList<MyShape> shapes = new ArrayList<MyShape>();

        MyLine line1 = new MyLine(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.RED);
        MyLine line2 = new MyLine(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.GREEN);
        MyRectangle rect1 = new MyRectangle(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.ORANGE, true);
        MyRectangle rect2 = new MyRectangle(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.BLACK, false);
        MyOval oval1 = new MyOval(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.PINK, true);
        MyOval oval2 = new MyOval(randomInt(200), randomInt(200), randomInt(200), randomInt(200), Color.GRAY, false);

        shapes.add(line1);
        shapes.add(line2);
        shapes.add(rect1);
        shapes.add(rect2);
        shapes.add(oval1);
        shapes.add(oval2);

        return shapes;
    }

    public static int randomInt(int max)
    {
        Random random = new Random();
        return random.nextInt(max);
    }
}
