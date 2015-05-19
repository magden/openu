/**
 * Created by Stas on 10/04/2015 19:48.
 */

public class Program
{
    public static void main(String args[])
    {
        System.out.print("Maman 14 ex1");

        Book javaBook = new Book("Java: How to Program", new String[]{"H.M. Deitel", "P.J. Deitel"}, 2015, "Prentice Hall");
        Book book1 = new Book("And Then There Were None", new String[]{"Agatha Christie"}, 1939, "Mr Publisher inc.");
        Book book2 = new Book("A Tale Of Two Cities", new String[]{"Charles Dickens"}, 1859, "Mr Publisher inc.");

        Book[] books = {javaBook, book1, book2};
        Double[] prices = {100.0, 54.40, 33.33, 4.4};
        try
        {
            AssociationTable<Book, Double> table = new AssociationTable<Book, Double>(books, prices);
        }
        catch (InvalidArgumentException e)
        {
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }




    }
}
