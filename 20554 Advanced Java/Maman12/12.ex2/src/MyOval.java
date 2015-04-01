import java.awt.*;

/**
 * Created by Stas on 01/04/2015.
 */
public class MyOval extends MyBoundedShape
{
    public MyOval(int boundRectOriginX, int boundRectOriginY, int boundRectWidth, int boundRectHeight, Color color, boolean fill)
    {
        super(boundRectOriginX, boundRectOriginY, boundRectWidth, boundRectHeight, color, fill);
    }

    @Override
    public void draw(Graphics graphicsInstance)
    {
        Color oldColor = graphicsInstance.getColor();
        graphicsInstance.setColor(this.getShapeColor());
        if (this.isFull)
        {
            graphicsInstance.drawOval(getOriginX(), getOriginY(), getWidth(), getHeight());
        }
        else
        {
            graphicsInstance.drawOval(getOriginX(), getOriginY(), getWidth(), getHeight());
        }
        graphicsInstance.setColor(oldColor);
    }
}
