package io.github.subtlelib.poi.impl.column;

/**
 * Created on 02/04/13
 *
 * @author d.serdiuk
 */
public class Columns {
    private static final int OFFSET_TO_A = 64;

    public static String columnIndexAsLetters(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index must be positive");
        }
        if (index > 702) {
            throw new IllegalArgumentException("only 702 columns supported now");
        }

        int first = index / 27;
        int second = index - first*26;

        String firstChar = first == 0 ? "" : String.valueOf((char)(first + OFFSET_TO_A));
        char secondChar = (char)(second + OFFSET_TO_A);
        return firstChar + secondChar;
    }
}
