import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Stas on 20/05/2015 20:13.
 */

public class ViewController
{
    public JPanel pictureCanvas; //ref to view object
    private Picture<MyShape> pictureModel;
    private JFrame window;

    public ViewController(JFrame window)
    {
        this.window = window;
        View view = new View(this);
        this.pictureModel = new Picture<MyShape>();
        window.add(view);

        window.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent componentEvent)
            {
                drawPicture();
            }
        });
    }

    //events
    public void screenClick(int x, int y)
    {
        pictureModel.remove(new Point(x, y));
        drawPicture();
    }

    public void addLine(MyLine line)
    {
        pictureModel.add(line);
        drawPicture();
    }

    public void addOval(MyOval oval)
    {
        pictureModel.add(oval);
        drawPicture();
    }

    public void addRect(MyRectangle rect)
    {
        pictureModel.add(rect);
        drawPicture();
    }

    public void drawPicture()
    {
        Graphics g = pictureCanvas.getGraphics();
        g.clearRect(0, 0, window.getWidth(), window.getHeight());
        pictureModel.show(pictureCanvas.getGraphics());
    }

    public void screenSizeChanged()
    {
        drawPicture();
    }
}
