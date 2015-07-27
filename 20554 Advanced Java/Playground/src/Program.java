import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Stas on 06/07/2015 20:40.
 */

public class Program
{
    public static void main (String [] args)
    {
        JFrame window = new JFrame("title");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.add(new Q2Panel(), BorderLayout.NORTH);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        aaa(1, new JPanel());

    }


    public static <T,E extends JPanel> E aaa(T obj,E f)
    {
        return null;
    }
}
