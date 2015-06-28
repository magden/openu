import javax.swing.*;

/**
 * Created by Stas on 26/06/2015 22:27.
 */

public class Program
{
    public static void main(String [] args)
    {
        JFrame window = new JFrame("MuliThreading");
        window.setSize(600,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.add(new MainPanel());
        window.setVisible(true);
    }
}
