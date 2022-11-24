package views;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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

public class UserView extends JFrame implements ActionListener   {
    JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox dropDownBox1;
    JComboBox dropDownBox2;
    JButton b1;
    Connection con = null;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Book ID","Book Name","Available ?","Lend To"};
    String from;
    int totalNum;
    String[] idArray;
    int id;
    String borrowerName;
    DefaultTableModel model;
    
    
	public UserView() {
		try {
			con = new DataBaseUtility().getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    

    
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
    	BookDao bookDao = new BookDao();
    	
        frame1 = new JFrame("UserView");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(new BorderLayout());
       
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
        //frame1.add(dropDownBox1);
        
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(600,400));
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
        	       
        totalNum = BookDao.getTotalNum(con);
        //2. Loop total Num of books times
        for(int i=1; i<totalNum + 1; i++) {    //bookID start with '1'
        	model.addRow( BookDao.getArrayOfOneBook(con,i)); 	
        }
        
        //3.Fill dropdown box
       //URL:javatpoint.com/java-jcombobox
      //URL: stackoverflow.com/questions/64350657/how-to-add-components-to-a-jframe-after-initialization       
        idArray = new String[totalNum+1];
        idArray[0]="      Chooes the Book ID to you want to borrow";
        for(int i=1; i< totalNum+1; i++){       	
        	idArray[i]= String.valueOf(i);
        }
        
        String[] nameArray = {"      Choose Your Names","Jim", "Lucy","Lily","Tom"};
         
        dropDownBox1 = new JComboBox(idArray);
        dropDownBox2 = new JComboBox(nameArray);
        
      //4.Add action to the dropdown box : 
     //stackoverflow.com/questions/14306125/how-to-use-actionlistener-on-a-combobox-to-give-a-variable-a-value
        dropDownBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 String s = (String) dropDownBox1.getSelectedItem();//get the selected item
				 id = Integer.parseInt(s); 	
				 System.out.println(id);
			}});     	
        
        dropDownBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrowerName = (String) dropDownBox2.getSelectedItem();//get the selected item
				System.out.println(borrowerName);
				try {
					BookDao.borrowBook(con,0,borrowerName,"5/5/22",id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
				//Delete all rows: URL:stackoverflow.com/questions/6232355/deleting-all-the-rows-in-a-jtable
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				//dtm.setRowCount(totalNum);
				//((AbstractTableModel) table.getModel()).fireTableCellUpdated(id, 3); // Repaint one cell.
				//((AbstractTableModel) table.getModel()).fireTableCellUpdated(id, 4); // Repaint one cell.
		        //1. Get the total number of rows (total number of books in database)
		        for(int i=1; i<totalNum + 1; i++) {    //bookID start with '1'
		        	try {
						model.addRow( BookDao.getArrayOfOneBook(con,i));
						System.out.println(i);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 	
		        }
				

								 
			}});  
        
        
        
        frame1.getContentPane().add(scroll,BorderLayout.SOUTH);
        frame1.getContentPane().add(dropDownBox1,BorderLayout.NORTH);
        frame1.getContentPane().add(dropDownBox2,BorderLayout.CENTER);
        frame1.setVisible(true);
        frame1.setSize(1200, 900);

    }
    
    public static void main(String args[]) {
    	
        try {
        	BookDao bookDao = new BookDao();
			new UserView().showTableData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}//nov 24 12 am