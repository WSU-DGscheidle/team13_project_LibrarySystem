package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import model.Books;
import utility_public.DataBaseUtility;

public class AddBookForm extends JFrame {

	//Default serial version ID since this class extends JFrame
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	
	JFrame frame1;
	private JTextField textBookName, textBookAuthor;
	private JLabel label, label_1, label_2;
	
	public AddBookForm(DefaultTableModel tableModel)
	{
		this.tableModel = tableModel;
	}

	public void showForm() {
		frame1 = new JFrame("Add Book");
		frame1.getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		label = new JLabel("Book Name: ");
		frame1.getContentPane().add(label);
		
		textBookName = new JTextField();
		frame1.getContentPane().add(textBookName);
		textBookName.setColumns(10);
		
		label_1 = new JLabel("Author: ");
		frame1.getContentPane().add(label_1);
		
		textBookAuthor = new JTextField();
		frame1.getContentPane().add(textBookAuthor);
		textBookAuthor.setColumns(10);
		
		//This label is so that the add book btn is in the right spot
		label_2 = new JLabel("");
		frame1.getContentPane().add(label_2);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataBaseUtility dbUtil = new DataBaseUtility(); 
				Connection con;
				try {
					con = dbUtil.getCon();

					JButton btnRemoveBook = new JButton("Remove");
			    	btnRemoveBook.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								BookDao.delete_byID(con, 1);
								tableModel.removeRow(1-1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					Object[] bookRow = {Integer.toString(tableModel.getRowCount()+1), textBookName.getText(), textBookAuthor.getText(), true, "", btnRemoveBook};
					tableModel.addRow(bookRow);
					
					BookDao.add(con, new Books(textBookName.getText(), tableModel.getRowCount()+1, 1, ""));
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		frame1.add(btnAddBook);
		
		frame1.setVisible(true);
        frame1.setSize(400, 100);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTable(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

}
