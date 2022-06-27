package examples.conditional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created on 16/05/13
 * @author d.serdiuk
 */
public class ConditionalReportModel {

    private final boolean ebooksIncluded;
    private final Map<Author, List<Book>> booksByAuthor;
    private final LocalDate reportCreationDate;
    private final String reportCreationPlace;
    private final Integer reportNumber;

    public ConditionalReportModel(boolean ebooksIncluded, Collection<Book> books, LocalDate reportCreationDate,
                                  String reportCreationPlace, Integer reportNumber) {
        this.ebooksIncluded = ebooksIncluded;
        booksByAuthor = books.stream().collect(Collectors.groupingBy(Book::getAuthor));
        this.reportCreationDate = reportCreationDate;
        this.reportCreationPlace = reportCreationPlace;
        this.reportNumber = reportNumber;
    }

    public boolean isEbooksIncluded() {
        return ebooksIncluded;
    }

    public Map<Author, List<Book>> getBooksByAuthor() {
        return booksByAuthor;
    }

    public LocalDate getReportCreationDate() {
        return reportCreationDate;
    }

    public String getReportCreationPlace() {
        return reportCreationPlace;
    }

    public static ConditionalReportModel getExample() {
        List<Author> authors = Arrays.asList(
                new Author("Barker", "Pat", Optional.of("5684562"), LocalDate.of(2012, 5, 5), "AA"),
                new Author("Eagleman", "David", Optional.of("122255"), LocalDate.of(2012, 5, 1), "A+"),
                new Author("Dostoevsky", "Fyodor", Optional.empty(), LocalDate.of(2010, 3, 10), "A+"),
                new Author("Frey", "James", Optional.empty(), LocalDate.of(1995, 1, 2), "B")
        );

        List<Book> books1 = Arrays.asList(
                new Book(authors.get(0), "Regeneration", 251, "OIJ911A", 4.2f, 56, "Plume", Optional.of("0452270073") ),
                new Book(authors.get(1), "Incognito: The Secret Lives of the Brain", 304, "634AJ21", 4.3f, 58, "Vintage", Optional.of("0307389928")),
                new Book(authors.get(2), "Crime and Punishment", 446, "9MZ115P", 4.4f, 19, "Simon & Brown", Optional.of("1613821824")),
                new Book(authors.get(3), "How to Write a Damn Good Novel: A Step-by-Step No Nonsense Guide to Dramatic Storytelling", 192, "722AP1", 4.5f, 50, "St. Martin's Press", Optional.empty()),
                new Book(authors.get(0), "Toby's Room", 320, "KJNKJB7", 4.0f, 52, "Doubleday", Optional.empty()),
                new Book(authors.get(1), "The Book of Barely Imagined Beings: A 21st Century Bestiary", 448, "KBB1166", 3.3f, 36, "University Of Chicago Press", Optional.of("022604470X")),
                new Book(authors.get(2), "The Idiot", 656, "ZXPL34I", 4.4f, 48, "Vintage", Optional.of("0375702245")),
                new Book(authors.get(0), "The Eye in the Door", 280, "91232O", 4.5f, 55, "Plume", Optional.of("0452272726")),
                new Book(authors.get(0), "The Ghost Road", 288, "KASD781", 4.1f, 43, "Plume", Optional.of("0452276721"))
        );

        return new ConditionalReportModel(true, books1, LocalDate.now(), "New York", 1436);
    }

    public Integer getReportNumber() {
        return reportNumber;
    }

}
