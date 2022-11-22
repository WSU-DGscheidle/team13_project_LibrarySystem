package model;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @class LibraryJTableButtonRenderer
 * This class allows the JButtons in the table to be rendered instead of just being the class as a string
 * 
 * @author Skyler Gentner
 *
 */
public class LibraryJTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer defaultRenderer;
	
	public LibraryJTableButtonRenderer(TableCellRenderer renderer) {
		defaultRenderer = renderer;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if(value instanceof Component)
			return (Component)value;
		return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
