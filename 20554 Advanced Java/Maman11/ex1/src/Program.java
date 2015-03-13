import javax.swing.*;

public class Program
{
    public static void main(String[] args) {
        // write your code here
        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello "+name,"Welcome",JOptionPane.INFORMATION_MESSAGE);
    }
}
