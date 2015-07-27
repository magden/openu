import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Created by Stas on 08/07/2015 23:23.
 */

public class NetThread extends Thread
{
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public NetThread(Socket socket)
    {
        try
        {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException ex) {
            System.out.println("Client Connection Error");
        }
        System.out.println("Client Connected");

    }

    @Override
    public void run()
    {
        //get client's request
        String line = null;
        String request = "";
        try
        {
            while (in.ready() && ((line = in.readLine()) != null)) {
                request+=line+"\n";
            }
            Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
            Matcher matcher = pattern.matcher(line);
        }
        catch (IOException e)
        {
            System.out.println("Error reading client request");
        }

        String response = "<html><head><title>Java server</title></head><body><h1>Test HTTP Server</h1></body></html>";
        this.out.println("HTTP/1.1 200 OK");
        this.out.println("Content-Type: text/html; charset=UTF-8");
        this.out.println("Content-length: " + response.length());
        this.out.println();
        this.out.println(response);

        //Scanner fileScan = new Scanner(new File("file.txt"));

        try
        {
            in.close();
            out.close();
            socket.close();
            System.out.println("Client Diconnected");

        }
        catch (IOException e)
        {
            System.out.println("Problem closing connection");
        }
    }

}
