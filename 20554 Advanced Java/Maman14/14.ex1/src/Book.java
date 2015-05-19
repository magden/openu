/**
 * Created by Stas on 20/05/2015 00:24.
 */

public class Book implements Comparable<Book>
{
    private String name;
    private String[] authors;
    private int publishYear;
    private String publisher;

    public Book(String name, String[] authors, int publishYear, String publisher)
    {
        this.name = name;
        this.authors = authors;
        this.publishYear = publishYear;
        this.publisher = publisher;
    }

    @Override
    public int compareTo(Book book)
    {
        return this.name.compareTo(book.name);
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public int getPublishYear()
    {
        return publishYear;
    }

    public void setPublishYear(int publishYear)
    {
        this.publishYear = publishYear;
    }

    public String[] getAuthors()
    {
        return authors;
    }

    public void setAuthors(String[] authors)
    {
        this.authors = authors;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
