package examples.conditional;

import org.joda.time.LocalDate;

import com.google.common.base.Optional;

/**
 * TODO: describe class
 * <p/>
 * Created on 16/05/13
 *
 * @author d.serdiuk
 */
public class Author {

    private final String surname;
    private final String name;
    private final Optional<String> contactNumber;
    private final LocalDate lastUpdate;
    private final String rating;

    public Author(String surname, String name, Optional<String> contactNumber, LocalDate lastUpdate,
                  String rating) {
        this.surname = surname;
        this.name = name;
        this.contactNumber = contactNumber;
        this.lastUpdate = lastUpdate;
        this.rating = rating;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getContactNumber() {
        return contactNumber;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public String getRating() {
        return rating;
    }
}
