/**
 * Created by Stas on 26/06/2015 22:27.
 */

public class Program
{
    public static void main(String [] args)
    {
        final int N = 100;
        final int M = 10;
        Integer [] numbers = new Integer[N];
        for (int i = 0; i < N; i++)
        {
            numbers[i] = i + 1;
        }


        ArrayRepository summer = new ArrayRepository(numbers, M);

        Thread [] threads = new Thread[M];

        for (int i = 0; i < M; i++)
        {
            threads[i] = new Thread(new NumberSumWorker(summer));
        }

        for (Thread t : threads)
        {
            t.start();
        }
    }
}
