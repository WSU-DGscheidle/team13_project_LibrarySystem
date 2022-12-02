package model;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @class LibraryJTableButtonRenderer
 * This class allows the JButtons in the table to be rendered instead of just being the class as a string <br>
 * This class helps satisfy the following requirements:
 * 	- There shall be buttons to add and delete books in the database
 * @author Skyler Gentner
 *
 */
public class LibraryJTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer defaultRenderer;
	
	/**
	 * Constructor that sets the default renderer to the parameter
	 * @param renderer
	 */
	public LibraryJTableButtonRenderer(TableCellRenderer renderer) {
		defaultRenderer = renderer;
	}
	
	/**
	 * @return the render component for the selected table cell
	 * @param table, value, isSelected, hasFocus, row, column
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if(value instanceof Component)
			return (Component)value;
		return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
