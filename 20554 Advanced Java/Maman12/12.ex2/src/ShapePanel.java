import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stas on 03/04/2015.
 */
public class ShapePanel extends JPanel
{
    private ArrayList<MyShape> shapes;

    public ShapePanel(ArrayList<MyShape> shapes)
    {
        this.shapes = shapes;
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        for (MyShape shape : shapes)
        {
            shape.draw(graphics);
        }
    }
}
