/**
 * Created by Stas on 26/06/2015 22:27.
 */

public class Program
{
    public static void main(String [] args)
    {
        ArrayRepository summer = new ArrayRepository(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Thread t1 = new Thread(new NumberSumWorker(summer));
        t1.run();
    }
}
