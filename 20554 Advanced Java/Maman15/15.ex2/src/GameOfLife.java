import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created by Stas on 13/03/2015.
 */
public class GameOfLife
{
    private boolean[][] currentGen;
    private boolean[][] nextGen;
    private boolean[][] nextGenChecklist; // to sync all threads
    private int nextGenCounter;
    private int totalCells;
    private final int GEN_DELAY = 1000;

    public GameOfLife(int height, int width)
    {
        nextGenCounter = 0;
        currentGen = new boolean[height][width];
        nextGen = new boolean[height][width];
        nextGenChecklist = new boolean[height][width];
        totalCells = height * width;
        for (int i = 0; i < currentGen.length; i++)
        {
            for (int j = 0; j < currentGen[i].length; j++)
            {
                currentGen[i][j] = false;
                nextGen[i][j] = false;
            }
        }
    }

    public boolean getCellState(int row, int col)
    {
        return currentGen[row][col];
    }

    public void setCellState(int row, int col, boolean state)
    {
        if (isValidCell(col, row))
        {
            currentGen[row][col] = state;
        }
    }

    private boolean isValidCell(int row, int col)
    {
        return row >= 0 && row < currentGen.length &&
                col >= 0 && col < currentGen[row].length;
    }

    public synchronized void setNextGenerationState(int row, int col, boolean state)
    {
        this.nextGen[row][col] = state;
        this.nextGenChecklist[row][col] = true;
        this.nextGenCounter++;

        //finished calculating all cells. Next generation is ready!
        if(this.nextGenCounter == totalCells) {
            advanceNextGeneration();
            notifyAll();
        }
    }

    private synchronized void advanceNextGeneration()
    {
        //reset everything and get ready for next gen
        nextGenCounter = 0;
        for (int i = 0; i < currentGen.length; i++)
        {
            for (int j = 0; j < currentGen[i].length; j++)
            {
                currentGen[i][j] = nextGen[i][j];
                nextGenChecklist[i][j] = false;
            }
        }

        //wait a little bit
        try
        {
            Thread.sleep(GEN_DELAY);
        }
        catch (InterruptedException e)
        {

        }
    }

    public synchronized int getLivingNeighbors(int row, int col)
    {
        //if we already calculated this gen, wait for next gen
        while (nextGenChecklist[row][col] == true) {
            try { wait(); }
            catch (InterruptedException ex) { }
        }

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
