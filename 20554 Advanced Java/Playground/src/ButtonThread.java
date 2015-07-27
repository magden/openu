import javax.swing.*;

/**
 * Created by Stas on 06/07/2015 20:52.
 */

class ButtonThread extends Thread
{
    private JButton btn;

    public ButtonThread(JButton btn)
    {
        this.btn = btn;
    }

    public void run()
    {
        int random = (int)(Math.random()*100);
        int currentNum = 0;
        try { currentNum = Integer.parseInt(btn.getText()); }
        catch (Exception ex) { currentNum = 0; }
        for (int i = 0; i< random ; i++) {
            currentNum = (currentNum+1)%10;
            btn.setText(currentNum+"");
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}