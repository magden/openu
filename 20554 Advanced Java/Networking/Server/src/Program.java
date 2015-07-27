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
    public static void main (String [] args)
    {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(7777);
        }
        catch (IOException e) {
            System.out.println("Could not listen to socket on port 7777");
            System.exit(1);
        }
        System.out.println("Server is listening on port 7777");

        boolean listening = true;
        while(listening)
        {
            Socket newClient = null;
            try
            {
                newClient = serverSocket.accept();
                new NetThread(newClient).start();
            }
            catch (IOException e)
            {
                System.out.println("accept failed. sorry.");
            }
        }


        try
        {
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
