package views;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import utility_public.DataBaseUtility;

/**
 * 
 *This Class is the User View Interface. 
 *It display the list of all books in the library after regular users login in.
 *It allows user to borrow a book by setting the book's isAvailable status to 1 or 0
 *Set Status 1 indicate this book is available. Set status to 0 indicate the book is not available.
 *
 * @author Joshua
 * 
 */

public class UserView extends JFrame implements ActionListener {
    JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Book ID","Book Name","Available ?","Lend To"};
    String from;
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
				showTableData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    public void showTableData() throws Exception {
        frame1 = new JFrame("UserView Title");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
       
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
       
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        //Example data
//        Object[] row1 = {1,"Harry Potter 1",1,""};
//        Object[] row2 = {2,"Harry Potter 2",1,""};
//        Object[] row3 = {3,"Harry Potter 3",0,"Lucy"};
//        model.addRow(row1);
//        model.addRow(row2);
//        model.addRow(row3);
        
        //1. Get the total number of rows (total number of books in database)
        DataBaseUtility dbUtil = new DataBaseUtility(); 
        Connection con = dbUtil.getCon();        
        int totalNum = BookDao.getTotalNum(con);
        //2. Loop total Num of books times
        for(int i=1; i<totalNum + 1; i++) {    //bookID start with '1'
        	model.addRow( BookDao.getArrayOfOneBook(i)); 	
        }
        
        
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }
    
    public static void main(String args[]) {
        try {
			new UserView().showTableData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}