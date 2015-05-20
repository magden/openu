import javax.swing.*;

/**
 * Created by Stas on 10/04/2015 19:48.
 */

public class Program
{
    public static void main(String args[])
    {
        JFrame window = new JFrame();
        window.setTitle("Shapes");
        window.setSize(800, 500);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new ViewController(window);
        window.setVisible(true);
    }
}
