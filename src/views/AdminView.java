package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.LibraryTableCheckBoxModelListener;
import model.LibraryJTable;
import model.LibraryJTableButtonRenderer;
import model.LibraryTableButtonMouseListener;
import utility_public.DataBaseUtility;
import java.awt.event.ActionListener;


/**
 * @class AdminView
 * This Class is the Administrator View Interface. 
 * It display the list of all books in the library after the Administrator login in.
 * It allows the Administrator to add,delete and edit book's information.
 *
 * @author Skyler
 * 
 */
public class AdminView extends JFrame {
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
    String[] columnNames = {"Book ID", "Book Name", "Author", "Availabile?", "Borrower", "Delete Book"};
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
    	
    	JButton btnRemoveBook = new JButton("Remove");
    	btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				e.getActionCommand();
				System.out.println("Remove 1");
			}
		});
        //Example data
        Object[] row = {"1", "Notes From Underground.", "Dostoyevsky", true, "Skyler G", btnRemoveBook};
        model.addRow(row);
		
        //This must be after the rows are added to the table so that it knows what to listen to
        table.getModel().addTableModelListener(new LibraryTableCheckBoxModelListener());
        table.addMouseListener(new LibraryTableButtonMouseListener(table));
        
        JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
			}
		});
		frame1.getContentPane().add(btnAddBook, BorderLayout.NORTH);
        frame1.getContentPane().add(scroll);
        frame1.setVisible(true);
        frame1.setSize(700, 300);
    }
    
    public static void main(String args[]) throws Exception {
        new AdminView().showTableData();
    }

}
