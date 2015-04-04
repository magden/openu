/**
 * Created by Stas on 04/04/2015.
 */
public class CPrint
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void red(Object msg)
    {
        System.out.print(ANSI_RED + msg + ANSI_RESET);
    }

    public static void green(Object msg)
    {
        System.out.print(ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void yellow(Object msg)
    {
        System.out.print(ANSI_YELLOW + msg + ANSI_RESET);
    }

    public static void blue(Object msg)
    {
        System.out.print(ANSI_BLUE + msg + ANSI_RESET);
    }

}
