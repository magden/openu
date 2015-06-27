import java.util.*;
import java.util.Arrays;

/**
 * Created by Stas on 27/06/2015 21:35.
 */

public class ArrayRepository
{
    List<Integer> intList;
    public ArrayRepository(Integer[] array)
    {
        intList = new ArrayList<Integer>(Arrays.asList(array));
    }

    public void insert(Integer item)
    {
        intList.add(item);
    }

    public Integer[] popTwoItems()
    {
        if (intList.size() < 2)
        {
            return null;
        }

        Integer [] poped = new Integer[2];
        poped[0] = intList.remove(0);
        poped[1] = intList.remove(0);

        return poped;
    }
}
