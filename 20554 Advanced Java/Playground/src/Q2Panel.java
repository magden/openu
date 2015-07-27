import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stas on 06/07/2015 20:55.
 */

class Q2Panel extends JPanel implements ActionListener {
    private JButton[] buttons;
    private final int NUM_BUTTONS = 4;

    public Q2Panel() {
        buttons = new JButton[NUM_BUTTONS];
        for(int i = 0 ; i< NUM_BUTTONS; i++) {
            buttons[i] = new JButton("0");
            buttons[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {

                }
            });

            add(buttons[i]);
        }
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        new ButtonThread((JButton)actionEvent.getSource()).start();
    }
}