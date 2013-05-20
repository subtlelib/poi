package examples.conditional;

import com.google.common.base.Function;
import com.google.common.base.Optional;

/**
 * TODO: describe class
 * <p/>
 * Created on 16/05/13
 *
 * @author d.serdiuk
 */
public class Book {

    private final Author author;
    private final String title;
    private final Integer pages;
    private final String ebookNumber;
    private final Float rating;
    private final Integer leftInWarehouse;
    private final String publisher;
    private final Optional<String> isbn;

    public Book(Author author, String title, Integer pages, String ebookNumber, Float rating,
                Integer leftInWarehouse, String publisher, Optional<String> isbn) {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.ebookNumber = ebookNumber;
        this.rating = rating;
        this.leftInWarehouse = leftInWarehouse;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }

    public String getEbookNumber() {
        return ebookNumber;
    }

    public Float getRating() {
        return rating;
    }

    public Integer getLeftInWarehouse() {
        return leftInWarehouse;
    }

    public String getPublisher() {
        return publisher;
    }

    public Optional<String> getIsbn() {
        return isbn;
    }

    public static final Function<Book, Author> _author = new Function<Book, Author>() {
        @Override
        public Author apply(Book book) {
            return book.getAuthor();
        }
    };
}
