import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Stas on 10/04/2015 19:48.
 */

public class Program
{
    public static void main(String args[])
    {
        //Create books
        Book javaBook = new Book("Java: How to Program", new String[]{"H.M. Deitel", "P.J. Deitel"}, 2015, "Prentice Hall");
        Book book1 = new Book("And Then There Were None", new String[]{"Agatha Christie"}, 1939, "Mr Publisher inc.");
        Book book2 = new Book("A Tale Of Two Cities", new String[]{"Charles Dickens"}, 1859, "Mr Publisher inc.");

        Book[] books = {javaBook, book1, book2};
        Double[] prices = {100.0, 54.40, 33.33};
        AssociationTable<Book, Double> table;
        try
        {
            table = new AssociationTable<Book, Double>(books, prices);
        }
        catch (InvalidArgumentException e)
        {
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }

        //Add new book
        Book anotherBook = new Book("The Holy Bible", new String[]{"God"}, 2005, "God");
        table.add(anotherBook, 74.7);

        //Update existing book price
        table.add(anotherBook, 99999.99);

        //delete existing book
        table.remove(book1);

        //print all books

        Iterator<Book> it = table.keyIterator();
        while (it.hasNext())
        {
            Book currentBook = it.next();
            double price = table.get(currentBook);
            System.out.println(currentBook.getName() + "\t" +
                    Arrays.toString(currentBook.getAuthors()) + "\t" +
                    currentBook.getPublisher() + "\t" +
                    currentBook.getPublishYear() + "\t" +
                    price + "$");
        }

    }
}
