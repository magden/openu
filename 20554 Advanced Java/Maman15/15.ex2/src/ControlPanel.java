import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stas on 28/06/2015 23:47.
 */

public class ControlPanel extends JPanel
{
    private JButton btnStart;
    private GridPanel grid;
    private ArrayList<LifeWorkerThread> threads;


    public ControlPanel(GridPanel grid)
    {
        this.grid = grid;
        this.threads = new ArrayList<LifeWorkerThread>();
        initializeUI();
    }

    private void initializeUI()
    {
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                btnStart.setText("restart");
                start();
            }
        });
        add(btnStart);
    }

    private void start()
    {

        int height = grid.getGridHeight();
        int width = grid.getGridWidth();

        GameOfLife game = new GameOfLife(height,width, grid);
        randomizeCells(game, height, width);
        game.render(grid);

        stopAllThreads();
        threads = new ArrayList<LifeWorkerThread>();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                LifeWorkerThread lifeWorker = new LifeWorkerThread(game, i, j);
                threads.add(lifeWorker);
            }
        }

        for (Thread t : threads)
        {
            t.start();
        }
    }

    private void randomizeCells(GameOfLife game, int height, int width)
    {
        Random randomizer = new Random();

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                boolean randomBool = randomizer.nextBoolean();
                game.setCellState(i, j, randomBool);
            }
        }
    }

    private void stopAllThreads()
    {
        for (LifeWorkerThread t : threads)
        {
            t.terminate();
        }
    }
}
