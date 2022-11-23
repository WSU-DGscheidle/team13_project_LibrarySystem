package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.User;
import utility_public.DataBaseUtility;
//import utility_public.EmptyStringException;
//import utility_public.NullStringException;
import utility_public.StringUtility;
import dao.UserDao;


/**
 * 
 *This Class is the Login User Interface. It takes in Login User's username and password,
 *connect to the online database if the given username and password match.
 *
 * @author Caihong
 * 
 */
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("User Name :");
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		
		//------------------------------------------for example: this is the Login Button------------------------------------------
		JButton btnNewButton = new JButton("Login"); //Create a button instance from JButton Class
		
		//The button instance  calls addActionListener（） method and pass a ActionListener interface object(using "new")  
		//when an action event is trigger,say,press the button,that interface's object method  actionPerformed() is called.
		btnNewButton.addActionListener(new ActionListener() {      			
			public void actionPerformed(ActionEvent e) {
				String userName= userNameTxt.getText();  //userNameTxt an object, it also is the member data of this LoginView Class
				String password = new String(passwordTxt.getPassword());  //getPassword() return char[],then autoboxing use new String() and pass it to reference called password
				
				//Verify if userName is null or not,if it is null pop-up a warning dialog button
				try {
					if(StringUtility.isNull(userName)) {
						JOptionPane.showMessageDialog(btnNewButton, "UserName must not be null!");
						return;
					}
						if(StringUtility.isEmpty(password)) {
							JOptionPane.showMessageDialog(btnNewButton, "UserName must not be empty!");
							return;
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//Verify if userName is empty or not,if it is empty pop-up a warning dialog button
	            //Removed redundunt if conditions.
				
				//Connect to the Database
				User user=new User(userName,password);  //create a User instance
				Connection con = null;  //create a reference called "con" and point to null;
				
				try {
					DataBaseUtility dbUtil = new DataBaseUtility();     //create a instance from DataBaseUtility Class
					
					con =dbUtil.getCon();  // using instance method getCon()  to return a connection object(from Connection Class)
					
					User currentUser = UserDao.login(con,user);  //Login() is a static method of UserDao Class,it return a user
					
					if(currentUser!=null) {
						JOptionPane.showMessageDialog(btnNewButton, "Login Success!");
						
						//Displays AdminView if login use username "Admin"
						if(user.getUserName().equals("Admin")) {
							dispose();   // Close current window in this case is LoginView
							AdminView adminView = new AdminView();  //Time to display the AdminView 
							adminView.setVisible(rootPaneCheckingEnabled); 
							
						}else {
							//Displays UserView if login success and is not "Admin"
							dispose(); 
							UserView userView = new UserView();
							userView.setVisible(rootPaneCheckingEnabled);
						}
						

						
					}else {
						
						JOptionPane.showMessageDialog(btnNewButton, "Login fail!");
					}
					
				}catch(Exception exception){
					
					
				}
				
			}
		});
		//------------------------------------------ End of Login Button ------------------------------------------
		
		
		JButton btnNewButton_1 = new JButton("Rest Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     //anonymous inner class
				//resetValueAction(e);
				passwordTxt.setText("");
				userNameTxt.setText("");
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/images/icons8-book-64.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(175, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(146))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton)
							.addComponent(lblNewLabel_2)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
						.addComponent(userNameTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
					.addGap(130))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(104)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}//LoginView
