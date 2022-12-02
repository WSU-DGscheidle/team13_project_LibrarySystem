package model;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

/**
 * @class LibraryJTable
 * Custom class used to modify a JTable to work for the Library System
 * 
 * @author Skyler Gentner
 *
 */
public class LibraryJTable extends JTable {
	
	//Default serial version ID since this class extends JTable
    private static final long serialVersionUID = 1L;

	public LibraryJTable() {
		// Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm) {
		super(dm);
		// Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// Auto-generated constructor stub
	}

	public LibraryJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// Auto-generated constructor stub
	}

	public LibraryJTable(Vector<? extends Vector<?>> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
		// Auto-generated constructor stub
	}

	public LibraryJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// Auto-generated constructor stub
	}
    
	/**
	 * Overridden function that returns the class for the specified column
	 * This helps satisfy the requirements:
	 * 	- There shall be check boxes next to the books to select and perform operations on the database
	 * 	- There shall be buttons to add and delete books in the database
	 */
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
            	return Integer.class;
            case 4:
                return Boolean.class;
            case 5: 
            	return String.class;
            case 6:
            	return JButton.class;
            default:
                return String.class;
        }
    }
    
    /**
     * Allows columns 4 and 5 (Available? and Borrower) to be editable while the others can't
     * This helps satisfy the following requirements:
     * 	- There shall be check boxes next to the books to select and perform operations on the database
     */
    @Override
    public boolean isCellEditable(int row, int col) {
         switch (col) {
             case 4:
             case 5:
                 return true;
             default:
                 return false;
          }
    }
}
