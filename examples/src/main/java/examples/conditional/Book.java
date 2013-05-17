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
    private final Optional<String> ebookNumber;
    private final Float rating;
    private final Integer leftInWarehouse;
    private final String publisher;
    private final String isbn;

    public Book(Author author, String title, Integer pages, Optional<String> ebookNumber, Float rating,
                Integer leftInWarehouse, String publisher, String isbn) {
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

    public Optional<String> getEbookNumber() {
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

    public String getIsbn() {
        return isbn;
    }

    public static final Function<Book, Author> _author = new Function<Book, Author>() {
        @Override
        public Author apply(Book book) {
            return book.getAuthor();
        }
    };
}
