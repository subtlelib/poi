package org.subtlelib.poi.impl.style;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.CompositeStyle;
import org.subtlelib.poi.api.style.SimpleStyle;
import org.subtlelib.poi.api.style.Style;

import com.google.common.base.Objects;

public class CompositeStyleImpl implements CompositeStyle {

	private final Map<Enum<?>, Style> styles = new HashMap<>();
	
	public CompositeStyleImpl() {
	}
	
	public CompositeStyleImpl(Style sourceStyle) {
		setStyle(sourceStyle);
	}

	public CompositeStyleImpl(SimpleStyle... styles) {
		for (SimpleStyle style : styles) {
			addStyle(style);
		}
	}
	
	@Override
	public Collection<Style> getStyles() {
		return styles.values();
	}

	@Override
	public void enrich(HSSFWorkbook workbook, HSSFCellStyle nativeStyle) {
		for (Style style : styles.values()) {
			style.enrich(workbook, nativeStyle);
		}
	}
	
	@Override
	public CompositeStyle setStyle(Style style) {
		if (style instanceof CompositeStyle) {
			CompositeStyle castedStyle = CompositeStyle.class.cast(style);
			for (Style compositeStyleItem : castedStyle.getStyles()) {
				addStyle(compositeStyleItem);
			}
		} else {
			addStyle(style);
		}
		
		return this;
	}
		
	private void addStyle(Style style) {
		if (style instanceof SimpleStyle) {
			checkArgument(!styles.containsKey(null), "Simultaneous use of SimpleStyle and Style is error-prone. Please use either one");
			SimpleStyle castedStyle = SimpleStyle.class.cast(style);
			styles.put(castedStyle.getType(), castedStyle);
		} else if (style instanceof Style) {
			checkArgument(styles.isEmpty() || styles.containsKey(null), "Simultaneous use of SimpleStyle and Style is error-prone. Please use either one");
			styles.put(null, style);
		} else {
			throw new IllegalArgumentException("Style of type '" + style.getClass() + "' is not supported");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getStyles());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompositeStyle) {
			CompositeStyle other = CompositeStyle.class.cast(obj);
			return Objects.equal(getStyles(), other.getStyles());
		}
		return false;
	}
	
}