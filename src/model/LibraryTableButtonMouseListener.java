package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 * @class LibraryTableButtonMouseListener
 * 
 * MouseListener that checks for when the mouse was clicked overtop of the table <br>
 * This class helps satisfy the following requirements:
 * 	- There shall be buttons to add and delete books in the database
 * @author Skyler Gentner
 */
public class LibraryTableButtonMouseListener implements MouseListener {
	private final JTable table;

    public LibraryTableButtonMouseListener(JTable table) {
      this.table = table;
    }
    
	public JTable getTable() {
		return table;
	}
    
	/**
	 * Overrides the mouseClicked function in MouseListener
	 * It calculates the column and row that was clicked and if it is in the table and is a JButton it will click the button
	 */
    @Override 
    public void mouseClicked(MouseEvent e) {
    	//Calculates
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row    = e.getY()/table.getRowHeight(); 

        if (row < table.getRowCount() && row >= 0 && column == 6) {
          Object value = table.getValueAt(row, column);
          if (value instanceof JButton) {
            ((JButton)value).doClick();
          }

        }
    }
    
    //This method is needed to implement MouseListener
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
    //This method is needed to implement MouseListener
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
    //This method is needed to implement MouseListener
	@Override
	public void mouseEntered(MouseEvent e) {

	}

    //This method is needed to implement MouseListener
	@Override
	public void mouseExited(MouseEvent e) {

	}
}
