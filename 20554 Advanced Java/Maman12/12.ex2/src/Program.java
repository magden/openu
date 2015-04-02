import javax.swing.*;
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
        //Shapes
        ArrayList<MyShape> shapes = createShapes();
        ArrayList<MyShape> clonedShapes = cloneShapes(shapes);
        changeShapes(clonedShapes);

        //Panel
        ArrayList<MyShape> allShapes = new ArrayList<MyShape>(shapes);
        allShapes.addAll(clonedShapes);
        ShapePanel panel = new ShapePanel(allShapes);

        //JFrame
        JFrame window = new JFrame();
        window.setTitle("Shapes ( ͡° \u035Cʖ ͡°)");
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static ArrayList<MyShape> createShapes()
    {
        ArrayList<MyShape> shapes = new ArrayList<MyShape>();
        Random random = new Random();

        MyLine line1 = new MyLine(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.RED);
        MyLine line2 = new MyLine(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.GREEN);
        MyRectangle rect1 = new MyRectangle(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.ORANGE, true);
        MyRectangle rect2 = new MyRectangle(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.BLACK, false);
        MyOval oval1 = new MyOval(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.PINK, true);
        MyOval oval2 = new MyOval(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200), Color.BLUE, false);

        shapes.add(line1);
        shapes.add(line2);
        shapes.add(rect1);
        shapes.add(rect2);
        shapes.add(oval1);
        shapes.add(oval2);

        return shapes;
    }

    private static ArrayList<MyShape> cloneShapes(ArrayList<MyShape> shapeArray)
    {
        ArrayList<MyShape> cloned = new ArrayList<MyShape>();
        for (MyShape shape : shapeArray)
        {
            try
            {
                cloned.add((MyShape) shape.clone());
            }
            catch (CloneNotSupportedException e)
            {
                System.out.println("something is wrong with the clone method :(");
            }
        }
        return cloned;
    }

    private static void changeShapes(ArrayList<MyShape> shapesArray)
    {
        for (MyShape shape : shapesArray)
        {
            shape.setX1(shape.getX1() + 10);
            shape.setY1(shape.getY1() + 10);

            if (shape instanceof MyBoundedShape)
            {
                MyBoundedShape boundedShape = (MyBoundedShape) shape;
                boundedShape.setIsFull(!boundedShape.isFull());
            }
        }
    }
}
