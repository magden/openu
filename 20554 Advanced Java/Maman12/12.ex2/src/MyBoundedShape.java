import java.awt.*;

/**
 * Created by Stas on 01/04/2015.
 */
public abstract class MyBoundedShape extends MyShape
{
    protected boolean isFull;

    public MyBoundedShape(int originX, int originY, int width, int height, Color color, boolean fill)
    {
        super(originX, width, originY, height, color);
        this.isFull = fill;
    }

    @Override
    public abstract void draw(Graphics graphicsInstance);

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        MyBoundedShape boundedShape = (MyBoundedShape) super.clone();
        boundedShape.isFull = this.isFull;

        return boundedShape;
    }

    public int getOriginX()
    {
        return this.x1;
    }

    public void setOriginX(int newOriginX)
    {
        this.x1 = newOriginX;
    }

    public int getOriginY()
    {
        return this.y1;
    }

    public void setOriginY(int newOriginY)
    {
        this.y1 = newOriginY;
    }

    public int getWidth()
    {
        return this.x2;
    }

    public void setWidth(int newWidth)
    {
        this.x2 = newWidth;
    }

    public int getHeight()
    {
        return this.y2;
    }

    public void setHeight(int newHeight)
    {
        this.y2 = newHeight;
    }
}
