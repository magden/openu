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

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof MyLine)
        {
            MyLine otherLine = (MyLine) obj;
            double length1 = Math.pow(this.getEndX() - this.getStartX(), 2) + Math.pow(this.getEndY() - this.getStartY(), 2);
            double length2 = Math.pow(otherLine.getEndX() - otherLine.getStartX(), 2) + Math.pow(otherLine.getEndY() - otherLine.getStartY(), 2);
            return length1 == length2;
        }
        return false;
    }

    @Override
    public boolean contains(int x, int y)
    {
        double m1 = (double) (x - this.x1) / (double) (y - this.y1);
        double m2 = (double) (this.x2 - x) / (double) (this.y2 - y);
        return m1 == m2;
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
