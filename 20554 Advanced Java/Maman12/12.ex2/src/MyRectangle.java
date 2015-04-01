import java.awt.*;

/**
 * Created by Stas on 01/04/2015.
 */
public class MyRectangle extends MyBoundedShape
{
    public MyRectangle(int originX, int originY, int width, int height, Color color, boolean fill)
    {
        super(originX, originY, width, height, color, fill);
    }

    @Override
    public void draw(Graphics graphicsInstance)
    {
        Color oldColor = graphicsInstance.getColor();
        graphicsInstance.setColor(this.getShapeColor());
        if (this.isFull)
        {
            graphicsInstance.drawRect(getOriginX(), getOriginY(), getWidth(), getHeight());
        }
        else
        {
            graphicsInstance.drawRect(getOriginX(), getOriginY(), getWidth(), getHeight());
        }
        graphicsInstance.setColor(oldColor);
    }
}
