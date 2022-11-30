package model;

import java.sql.Connection;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.BookDao;
import utility_public.DataBaseUtility;

public class LibraryTableCheckBoxModelListener implements TableModelListener {

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
