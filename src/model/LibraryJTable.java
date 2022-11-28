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
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(Vector<? extends Vector<?>> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public LibraryJTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}
    
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
     * Allows columns 3 and 4 (Available? and Borrower) to be editable while the others can't
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
