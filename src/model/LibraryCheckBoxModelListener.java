package model;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class LibraryCheckBoxModelListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        if (column == 3) {
        	TableModel model = (TableModel) e.getSource();
            if((Boolean) model.getValueAt(row, column)) {
            	System.out.println("true");
            } 
            else {
            	System.out.println("false");
            }
        }
	}

}
