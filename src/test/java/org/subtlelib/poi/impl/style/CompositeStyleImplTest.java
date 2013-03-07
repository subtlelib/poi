package org.subtlelib.poi.impl.style;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.subtlelib.poi.api.style.CompositeStyle;
import org.subtlelib.poi.api.style.SimpleStyle;
import org.subtlelib.poi.api.style.Style;

public class CompositeStyleImplTest {

	private enum StyleType {type1, type2};

	private class StyleTestImpl implements Style {

		private final String id;
		
		public StyleTestImpl(String id) {
			this.id = id;
		}

		@Override
		public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
			//do nothing
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		
		@Override
		public boolean equals(Object obj) {
			return Objects.equals(id, StyleTestImpl.class.cast(obj).id);
		}

	}

	private class SimpleStyleTestImpl implements SimpleStyle {

		private final String id;
		private final StyleType type;
		
		public SimpleStyleTestImpl(String id, StyleType type) {
			this.id = id;
			this.type = type;
		}

		@Override
		public Enum<?> getType() {
			return type;
		}

		@Override
		public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
			//do nothing
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, type);
		}
		
		@Override
		public boolean equals(Object obj) {
			SimpleStyleTestImpl other = SimpleStyleTestImpl.class.cast(obj);
			return Objects.equals(id, other.id)
					&& Objects.equals(type, other.type);
		}
		
	}
	
	@Test
	public void testSetStyle_styleAdd() {
		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(new StyleTestImpl("onlyStyle"));
		
		assertEquals(1, composite.getStyles().size());
	}

	@Test
	public void testSetStyle_styleOverwrite() {
		StyleTestImpl firstStyle = new StyleTestImpl("firstStyle");
		StyleTestImpl secondStyle = new StyleTestImpl("secondStyle");
		
		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(firstStyle);
		composite.setStyle(secondStyle);
		
		assertEquals(1, composite.getStyles().size());
		assertEquals(secondStyle, composite.getStyles().iterator().next());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStyle_styleMixWithSimpleStyle() {
		SimpleStyleTestImpl type1Style = new SimpleStyleTestImpl("type1Style", StyleType.type1);
		StyleTestImpl firstStyle = new StyleTestImpl("firstStyle");
		
		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(type1Style);
		composite.setStyle(firstStyle);
	}
	
	@Test
	public void testSetStyle_simpleStyleAdd() {
		SimpleStyleTestImpl type1Style = new SimpleStyleTestImpl("type1Style", StyleType.type1);
		SimpleStyleTestImpl type2Style = new SimpleStyleTestImpl("type2Style", StyleType.type2);

		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(type1Style);
		composite.setStyle(type2Style);
		
		assertEquals(2, composite.getStyles().size());
		assertTrue(composite.getStyles().contains(type1Style));
		assertTrue(composite.getStyles().contains(type2Style));
	}

	@Test
	public void testSetStyle_simpleStyleOverwrite() {
		SimpleStyleTestImpl type1Style1 = new SimpleStyleTestImpl("type1Style1", StyleType.type1);
		SimpleStyleTestImpl type2Style1 = new SimpleStyleTestImpl("type2Style1", StyleType.type2);
		
		SimpleStyleTestImpl type1Style2 = new SimpleStyleTestImpl("type1Style2", StyleType.type1);
		SimpleStyleTestImpl type2Style2 = new SimpleStyleTestImpl("type2Style2", StyleType.type2);

		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(type1Style1);
		composite.setStyle(type2Style1);
		composite.setStyle(type1Style2);
		composite.setStyle(type2Style2);
		
		assertEquals(2, composite.getStyles().size());
		assertTrue(composite.getStyles().contains(type1Style2));
		assertTrue(composite.getStyles().contains(type2Style2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStyle_simpleStyleMixWithStyle() {
		StyleTestImpl firstStyle = new StyleTestImpl("firstStyle");
		SimpleStyleTestImpl type1Style = new SimpleStyleTestImpl("type1Style", StyleType.type1);
		
		CompositeStyle composite = new CompositeStyleImpl();
		composite.setStyle(firstStyle);
		composite.setStyle(type1Style);
	}
	
}