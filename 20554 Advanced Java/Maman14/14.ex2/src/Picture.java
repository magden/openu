import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Stas on 20/05/2015 19:46.
 */

public class Picture<E extends MyShape>
{
    private ArrayList<E> shapeList;

    public Picture()
    {
        shapeList = new ArrayList<E>();
    }

    public void add(E shape)
    {
        this.shapeList.add(shape);
    }

    public ArrayList<E> get(Point p)
    {
        ArrayList<E> machedShapes = new ArrayList<E>();
        for (E shape : shapeList)
        {
            if (shape.contains(p.x, p.y))
            {
                machedShapes.add(shape);
            }
        }

        return machedShapes.size() > 0 ? machedShapes : null;
    }

    public int remove(Point p)
    {
        ListIterator<E> it = this.shapeList.listIterator();
        int removedShapes = 0;
        while (it.hasNext())
        {
            E currentShape = it.next();
            if (currentShape.contains(p.x, p.y))
            {
                it.remove();
                removedShapes++;
            }
        }
        return removedShapes;
    }

    public void show(Graphics g)
    {
        for (E shape : this.shapeList)
        {
            shape.draw(g);
        }
    }
}
