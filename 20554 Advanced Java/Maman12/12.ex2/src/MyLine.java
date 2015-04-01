import java.awt.*;

/**
 * Created by Stas on 01/04/2015.
 */
public class MyLine extends MyShape
{
    public MyLine(int startX, int startY, int endX, int endY, Color color)
    {
        super(startX, endX, startY, endY, color);
    }

    @Override
    public void draw(Graphics graphicsInstance)
    {
        Color oldColor = graphicsInstance.getColor();
        graphicsInstance.setColor(this.getShapeColor());
        graphicsInstance.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
        graphicsInstance.setColor(oldColor);
    }

    public int getStartX()
    {
        return this.x1;
    }

    public void setStartX(int newStartX)
    {
        this.x1 = newStartX;
    }

    public int getStartY()
    {
        return this.y1;
    }

    public void setStartY(int newStartY)
    {
        this.y1 = newStartY;
    }

    public int getEndX()
    {
        return this.x2;
    }

    public void setEndX(int newEndX)
    {
        this.x2 = newEndX;
    }

    public int getEndY()
    {
        return this.y2;
    }

    public void setEndY(int newEndY)
    {
        this.y2 = newEndY;
    }
}
