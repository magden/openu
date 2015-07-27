import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Stas on 11/07/2015 00:34.
 */

public class SoldierGame extends JPanel implements ActionListener
{
    private static final int SIZE = 5;
    private JButton[][] buttons;
    private int count, posX, posY;

    public SoldierGame()
    {
        setLayout(new GridLayout(SIZE,SIZE));
        count = 0;
        posX = (int)(Math.random()*SIZE);
        posY = (int)(Math.random()*SIZE);
        buttons = new JButton[SIZE][SIZE];
        for (int i=0; i< SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
            {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        buttons[posY][posX].setText("*");
    }

    public int[] getPos(JButton b)
    {
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (buttons[y][x] == b)
                    return new int[]{ x, y };
         return null;
    }

    public boolean isValidMove(JButton b)
    {
        if (!b.getText().equals(""))
            return false;

        int [] pos = getPos(b);
        if (pos== null)
            return false;

        int x = pos[0];
        int y = pos[1];
        return (posX == x && posY == y+1) ||
                (posX == x && posY == y-1) ||
                (posX == x+1 && posY == y) ||
                (posX == x-1 && posY == y);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() instanceof JButton)
        {
            JButton b = (JButton)actionEvent.getSource();
            if(isValidMove(b)) {
                count++;
                buttons[posY][posX].setText(count + "");
                int[] newPos = getPos(b);
                posX = newPos[0];
                posY = newPos[1];
                buttons[posY][posX].setText("*");
            }
        }
    }

    public static void main(String [] args)
    {
        JFrame window = new JFrame("Soldier game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.add(new SoldierGame(), BorderLayout.CENTER);
        window.setVisible(true);

        Object x = window;
    }
}
