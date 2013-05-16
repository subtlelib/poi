package examples.conditional;

import java.util.Collection;

/**
 * TODO: describe class
 * <p/>
 * Created on 16/05/13
 *
 * @author d.serdiuk
 */
public class ConditionReportModel {

    private boolean ebooksIncluded;
    private final Collection<Author> authors;

    public boolean isEbooksIncluded() {
        return ebooksIncluded;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }
}
