/**
 * Created by Stas on 27/06/2015 22:09.
 */

public class NumberSumWorker implements Runnable
{
    private ArrayRepository repo;

    public NumberSumWorker(ArrayRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void run()
    {
        System.out.println("hello");
        Integer [] twoItems = repo.popTwoItems();
        while (twoItems != null)
        {
            int sum = twoItems[0] + twoItems[1];
            repo.insert(sum);
            System.out.println(sum + "["+twoItems[0]+","+twoItems[1]+"]");
            twoItems = repo.popTwoItems();
        }

        System.out.println("finished. ");
    }
}
