import java.awt.*;

/**
 * Created by Stas on 01/04/2015.
 */
public abstract class MyShape implements Cloneable
{
    protected int x1, x2, y1, y2;
    protected Color shapeColor;

    public MyShape(int x1, int x2, int y1, int y2, Color color)
    {

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.shapeColor = color;
    }

    public abstract void draw(Graphics graphicsInstance);

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        MyShape shape = (MyShape) super.clone();
        shape.x1 = this.x1;
        shape.x2 = this.x2;
        shape.y1 = this.y1;
        shape.y2 = this.y2;
        shape.shapeColor = this.shapeColor;
        return shape;
    }

    public Color getShapeColor()
    {
        return this.shapeColor;
    }

    public void setShapeColor(Color newShapeColor)
    {
        this.shapeColor = newShapeColor;
    }
}
