package model;

import java.sql.Connection;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.BookDao;
import utility_public.DataBaseUtility;

/**
 * @class LibraryTableCheckBoxModelListener
 * 
 * This class listens to see if the checkbox inside of table is pressed <br>
 * This class helps satisfy the following requirements:
 * 	- There shall be check boxes next to the books to select and perform operations on the database
 * 
 * @author Skyler Gentner
 */
public class LibraryTableCheckBoxModelListener implements TableModelListener {

	/**
	 * Called when the table is changed in anyway <br>
	 * However it only does anything when the checkbox column was changed
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        if (column == 4) {
        	TableModel model = (TableModel) e.getSource();
        	DataBaseUtility dbUtil = new DataBaseUtility(); 
        	Connection con;
        	try {
				con = dbUtil.getCon();
				
				//This function modifies the database which satisfies that part of the requirement
            	BookDao.returnBook(con, ((Boolean)model.getValueAt(row, column)) ? 1 : 0, "", "", (int)model.getValueAt(row, 1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}

}
