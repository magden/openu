import javax.swing.*;
import java.awt.*;

/**
 * Created by Stas on 20/05/2015 20:13.
 */

public class ViewController
{
    public JPanel pictureCanvas; //ref to view object
    private Picture<MyShape> pictureModel;

    public ViewController(JFrame window)
    {
        View view = new View(this);
        this.pictureModel = new Picture<MyShape>();
        window.add(view);
    }

    //events
    public void screenClick(int x, int y)
    {

    }

    public void addLine(MyLine line)
    {
        pictureModel.add(line);
    }

    public void addOval(MyOval oval)
    {
        pictureModel.add(oval);
    }

    public void addRect(MyRectangle rect)
    {
        pictureModel.add(rect);
    }
}
