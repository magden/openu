import java.util.*;
import java.util.Arrays;

/**
 * Created by Stas on 27/06/2015 21:35.
 */

public class ArrayRepository
{
    private List<Integer> intList;
    private int waitingThreads;
    private int maxThreads;
    private boolean isFinished;

    public ArrayRepository(Integer[] array, int maxThreads)
    {
        this.maxThreads = maxThreads;
        intList = new ArrayList<Integer>(Arrays.asList(array));
        waitingThreads = 0;
        isFinished = false;
    }

    public synchronized void insert(Integer item)
    {
        intList.add(0, item);
        notifyAll();
    }

    public synchronized Integer[] popTwoItems()
    {
        while (intList.size() < 2)
        {
            if (waitingThreads < maxThreads - 1)
            {
                waitingThreads ++;
                //wait for a new number
                try {  wait(); }
                catch (InterruptedException e)  { }
                waitingThreads--;
            }
            else {
                //all threads are already waiting -> this is the last thread
                isFinished = true;
                notifyAll();
                return null;
            }

            if (isFinished) {
                return null;
            }
        }

        if (isFinished) {
            return null;
        }

        Integer [] poped = new Integer[2];
        poped[0] = intList.remove(0);
        poped[1] = intList.remove(0);

        return poped;
    }

    public Integer firstItem()
    {
        return intList.get(0);
    }
}
