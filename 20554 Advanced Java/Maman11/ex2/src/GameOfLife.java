/**
 * Created by Stas on 13/03/2015.
 */
public class GameOfLife
{
    private Boolean[][] currentGen;

    public GameOfLife(int height, int width)
    {
        currentGen = new Boolean[height][width];
        for (int i = 0; i < currentGen.length; i++)
        {
            for (int j = 0; j < currentGen[i].length; j++)
            {
                currentGen[i][j] = false;
            }
        }
    }

    public void setCellState(int row, int col, Boolean state)
    {
        if (isValidCell(col, row))
        {
            currentGen[row][col] = state;
        }
    }

    private Boolean isValidCell(int row, int col)
    {
        return row >= 0 && row < currentGen.length &&
                col >= 0 && col < currentGen[row].length;
    }

    public void nextGeneration()
    {
        Boolean[][] nextGen = currentGen.clone();

        for (int i = 0; i < currentGen.length; i++)
        {
            for (int j = 0; j < currentGen[i].length; j++)
            {
                int neighbors = livingNeighbors(i, j);

                //birth
                if (!currentGen[i][j] && neighbors == 3)
                {
                    nextGen[i][j] = true;
                }

                //life
                else if (currentGen[i][j] && neighbors >= 2 && neighbors <= 3)
                {
                    nextGen[i][j] = true;
                }

                //death
                else
                {
                    nextGen[i][j] = false;
                }
            }
        }

        currentGen = nextGen;
    }

    private int livingNeighbors(int row, int col)
    {
        int living = 0;

        //Top row
        if (isValidCell(row - 1, col - 1) && currentGen[row - 1][col - 1])
        {
            living++;
        }

        if (isValidCell(row - 1, col) && currentGen[row - 1][col])
        {
            living++;
        }

        if (isValidCell(row - 1, col + 1) && currentGen[row - 1][col + 1])
        {
            living++;
        }


        //Middle row
        if (isValidCell(row, col - 1) && currentGen[row][col - 1])
        {
            living++;
        }

        if (isValidCell(row, col + 1) && currentGen[row][col + 1])
        {
            living++;
        }


        //Bottom Row
        if (isValidCell(row + 1, col - 1) && currentGen[row + 1][col - 1])
        {
            living++;
        }

        if (isValidCell(row + 1, col) && currentGen[row + 1][col])
        {
            living++;
        }

        if (isValidCell(row + 1, col + 1) && currentGen[row + 1][col + 1])
        {
            living++;
        }

        return living;

    }

    @Override
    public String toString()
    {
        String output = "";
        for (int i = 0; i < currentGen.length; i++)
        {
            for (int j = 0; j < currentGen[i].length; j++)
            {
                output += currentGen[i][j] ? " + " : " - ";
            }
            output += " \n";
        }

        return output;
    }
}
