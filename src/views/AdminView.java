package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.LibraryCheckBoxModelListener;
import model.LibraryJTable;
import utility_public.DataBaseUtility;


/**
 * 
 *This Class is the Administrator View Interface. 
 *It display the list of all books in the library after the Administrator login in.
 *It allows the Administrator to add,delete and edit book's information.
 *
 * @author Skyler
 * 
 */
public class AdminView extends JFrame {

	JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    String ids;
    static JTable table;
    String[] columnNames = {"Book ID", "Book Name", "Author", "Availabile?", "Borrower"};
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
        frame1.setLayout(new BorderLayout());
       
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
       
        table = new LibraryJTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        DataBaseUtility dbUtil = new DataBaseUtility(); 
    	con = dbUtil.getCon();
    	
        //Example data
        Object[] row = {"1", "Notes From Underground.", "Dostoyevsky", true, "Skyler G"};
        model.addRow(row);
		
        //This must be after the rows are added to the table so that it knows what to listen to
        table.getModel().addTableModelListener(new LibraryCheckBoxModelListener());
        
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }
    
    public static void main(String args[]) throws Exception {
        new AdminView().showTableData();
    }

}
