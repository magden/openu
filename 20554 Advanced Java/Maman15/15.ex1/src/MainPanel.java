import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Stas on 28/06/2015 01:34.
 */

public class MainPanel extends JPanel
{
    private JTextField txtNumbers;
    private JTextField txtThreads;
    private JTextField txtSum;

    public MainPanel()
    {
        initUI();
    }

    public void initUI()
    {
        JLabel lblNumbers = new JLabel("Array size:");
        txtNumbers = new JTextField(4);

        JLabel lblThreads = new JLabel("Number of threads");
        txtThreads = new JTextField(2);

        JButton btnRun = new JButton("Start");
        btnRun.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                calculate();
            }
        });

        JLabel lblSum = new JLabel("Sum");
        txtSum = new JTextField(7);
        txtSum.setEditable(false);


        add(lblNumbers);
        add(txtNumbers);

        add(lblThreads);
        add(txtThreads);

        add(btnRun);

        add(lblSum);
        add(txtSum);
    }

    public void calculate()
    {
        int n = Integer.parseInt(txtNumbers.getText());
        int m = Integer.parseInt(txtThreads.getText());

        Integer [] numbers = new Integer[n];
        for (int i = 0; i < n; i++)
        {
            numbers[i] = (int)(Math.random()*100);
        }

        ArrayRepository summer = new ArrayRepository(numbers, txtSum, m);
        Thread [] threads = new Thread[m];
        for (int i = 0; i < m; i++)
        {
            threads[i] = new Thread(new NumberSumWorker(summer));
        }

        for (Thread t : threads)
        {
            t.start();
        }
    }
}
