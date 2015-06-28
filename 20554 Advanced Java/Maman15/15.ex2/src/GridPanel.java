import javax.swing.*;
import java.awt.*;

/**
 * Created by Stas on 28/06/2015 23:47.
 */

public class GridPanel extends JPanel
{
    private JPanel [][] panels;
    private int gridHeight;
    private int gridWidth;

    public GridPanel(int height, int width)
    {
        this.gridHeight = height;
        this.gridWidth = width;
        GridLayout gridLayout = new GridLayout(height, width);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        this.setLayout(gridLayout);
        panels = new JPanel[height][width];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Color.white);
                add(panels[i][j]);
            }
        }
    }

    public void setState(int row, int col, boolean state)
    {
        if (row < panels.length && col < panels[row].length)
        {
            Color color = state ? Color.BLACK : Color.WHITE;
            panels[row][col].setBackground(color);
        }
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public int getGridWidth()
    {
        return gridWidth;
    }
}
