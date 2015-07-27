import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created by Stas on 06/07/2015 20:40.
 */

public class Program
{
    public static void main (String [] args) throws IOException
    {
        String host = "localhost";
        System.out.println("Client");
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try
        {
            socket = new Socket(host, 7777);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }
        catch (IOException ex) {
            System.out.println("Could not connect to host");
        }

        System.out.println("Connected to host");

        String serverInput = "";
        while ((serverInput = in.readLine()) != null) {
            System.out.println(serverInput);
        }

        try
        {
            in.close();
            socket.close();
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
