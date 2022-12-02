/**
 * @file AdminView.java
 */
package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dao.BookDao;
import model.LibraryTableCheckBoxModelListener;
import model.Books;
import model.LibraryJTable;
import model.LibraryJTableButtonRenderer;
import model.LibraryTableButtonMouseListener;
import utility_public.DataBaseUtility;
import java.awt.event.ActionListener;


/**
 * @class AdminView
 * This Class is the Administrator View Interface. <br>
 * It display's the list of all books in the library after the Administrator logs in. <br>
 * It allows the Administrator to add, delete, and edit any book's information.
 * @author Skyler Gentner
 * 
 */
public class AdminView extends JFrame {
	/**
	 * Default Constructor
	 */
	public AdminView() {
	}

	//Default serial version ID since this class extends JFrame
	private static final long serialVersionUID = 1L;
	
	JFrame frame1;
    JLabel l0, l1, l2;
    JButton b1;
    Connection con;
    String ids;
    static JTable table;
    String[] columnNames = {"Book ID", "Book Name", "Author", "Quantity", "Availabile?", "Borrower", "Delete Book"}; 
    String from;
    
    /**
     * Called when the Add Book button is pressed <br>
     * This will open the AddBookForm.java
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
            	makeViewWindow();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /**
     * Makes the AdminView window <br>
     * This function helps satisfy the following requirements: <br>
     * 	- There shall be at least four columns displaying the database: Book name, Book ID, Book Availability, Borrower Username
     * 	- There shall be check boxes next to the books to select and perform operations on the database
     * 	- There shall be buttons to add and delete books in the database
     * 	- There shall be a column displaying the available quantity of the books
     * 	- Use the database information in the AdminView
     * @throws Exception
     */
    public void makeViewWindow() throws Exception {
        frame1 = new JFrame("Admin View");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(new BorderLayout());
       
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
       
        table = new LibraryJTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        TableCellRenderer tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new LibraryJTableButtonRenderer(tableRenderer));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        DataBaseUtility dbUtil = new DataBaseUtility(); 
    	con = dbUtil.getCon();

    	int[] ids = BookDao.getArrayOfID(con);
    	
    	for(int id : ids)
    	{
	    	JButton btnRemoveBook = new JButton("Remove");
	    	btnRemoveBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						BookDao.delete_byID(con, id);
						model.removeRow(table.getSelectedRow());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	    	Object[] book = BookDao.getArrayOfOneBook(con, id);
	    	int quantity = BookDao.getBookQuantity(con, (String)book[1]);
	        Object[] row = {book[0], book[1], "", quantity, ((int)book[2] > 0) ? true : false, book[3], btnRemoveBook};
	        model.addRow(row);
    	}
        JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookForm addBookForm = new AddBookForm(model);
				addBookForm.showForm();
			}
		});
		
        //This must be after the rows are added to the table so that it knows what to listen to
        table.getModel().addTableModelListener(new LibraryTableCheckBoxModelListener());
        table.addMouseListener(new LibraryTableButtonMouseListener(table));
        
		frame1.getContentPane().add(btnAddBook, BorderLayout.NORTH);
        frame1.getContentPane().add(scroll);
        frame1.setVisible(true);
        frame1.setSize(700, 300);
    }
    
    /**
     * Called to open the AdminView window
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        new AdminView().makeViewWindow();
    }

}
