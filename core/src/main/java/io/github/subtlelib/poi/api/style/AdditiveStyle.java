package io.github.subtlelib.poi.api.style;

/**
 * Marks the styles that can be combined together, adding features that they carry.
 * The behavior of adding styles is in Styles class.
 *
 * May be implemented by user-defined concrete styles.
 * <p><b> Implementations must be immutable </b></p>
 * <p>
 * (Ideally, AdditiveStyle would be an abstract class and that behavior would go there,
 * but enums cannot extend abstract classes (enum is the most convenient way to define user style).
 * </p>
 * Created on 10/04/13
 * @author d.serdiuk
 */
public interface AdditiveStyle extends Style {
    Enum<?> getType();
}
