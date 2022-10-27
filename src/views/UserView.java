package views;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    String[] columnNames = {"Book ID", "Book Name", "Author"};
    String from;
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            showTableData();
        }
    }
    public void showTableData() {
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
        Object[] row = {"1", "Notes From Underground.", "Dostoyevsky"};
        model.addRow(row);
		
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }
    
    public static void main(String args[]) {
        new UserView().showTableData();
    }
}
