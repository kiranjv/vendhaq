package com.vendhaq.layout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.vendhaq.repos.ContactsRepository;
import com.vendhaq.repos.LocalDbConfiguration;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AddCustomerView extends javax.swing.JFrame implements
		FocusListener {
	private JLabel label_headderlogo;
	private JTextField field_firstname;
	private JTextField field_company;
	private JTextField field_mobile;
	private JLabel label_birthdate;
	private JTextField field_birthday;
	private JButton button_cancle;
	private JButton button_save;
	private JComboBox combobox_countrys;
	private JTextField field_state;
	private JTextField field_city;
	private JTextField field_postcode;
	private JTextField field_address3;
	private JLabel label_physicaladdress;
	private JTextField field_address2;
	private JTextField field_address1;
	private JComboBox combo_customers;
	private JRadioButton radio_male;
	private ButtonGroup buttonGroup1;
	private JRadioButton radio_female;
	private JLabel label_gender;
	private JTextField field_birthyear;
	private JTextField field_birthmonth;
	private JTextField field_email;
	private JTextField field_customercode;
	private JLabel label_customerdetails;
	private JTextField field_lastname;
	private ContactsRepository contactsRepo;

	private static HashMap<String, String> BackgroundText_HashMap = new HashMap<String, String>();

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddCustomerView inst = new AddCustomerView();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public AddCustomerView() {
		super();
		contactsRepo = new ContactsRepository();
		initGUI();
	}

	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			getContentPane().addFocusListener(this);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				label_headderlogo = new JLabel();
				getContentPane().add(label_headderlogo);
				label_headderlogo
						.setIcon(new ImageIcon(
								getClass()
										.getClassLoader()
										.getResource(
												"com/vendhaq/layout/images/newcustomer_headder_logo.png")));
				label_headderlogo.setBounds(0, 0, 612, 48);
			}
			{
				field_firstname = new JTextField();
				getContentPane().add(field_firstname);
				field_firstname.setBounds(12, 122, 135, 30);
				field_firstname.setToolTipText("First");
				BackgroundText_HashMap.put("First", "First");
				field_firstname.setText(BackgroundText_HashMap.get("First"));
				field_firstname.addFocusListener(this);
			}
			{
				field_lastname = new JTextField();
				getContentPane().add(field_lastname);
				field_lastname.setBounds(167, 122, 133, 30);
				field_lastname.setToolTipText("Last");
				BackgroundText_HashMap.put("Last", "Last");
				field_lastname.setText(BackgroundText_HashMap.get("Last"));
				field_lastname.addFocusListener(this);

			}
			{
				label_customerdetails = new JLabel();
				getContentPane().add(label_customerdetails);
				label_customerdetails.setText("Customer details");
				label_customerdetails.setBounds(12, 74, 149, 33);
				label_customerdetails.setFont(new Font("Arial", Font.BOLD, 12));
			}
			{
				field_company = new JTextField();
				getContentPane().add(field_company);
				field_company.setBounds(12, 165, 135, 27);
				field_company.setToolTipText("Company");
				BackgroundText_HashMap.put("Company", "Company");
				field_company.setText(BackgroundText_HashMap.get("Company"));
				field_company.addFocusListener(this);

			}
			{
				field_customercode = new JTextField();
				getContentPane().add(field_customercode);
				field_customercode.setBounds(172, 165, 123, 27);
				field_customercode.setToolTipText("Customer code");
				BackgroundText_HashMap.put("Customer code", "Customer code");
				field_customercode.setText(BackgroundText_HashMap
						.get("Customer code"));
				field_customercode.addFocusListener(this);

			}
			{
				field_mobile = new JTextField();
				getContentPane().add(field_mobile);
				field_mobile.setBounds(12, 208, 283, 30);
				field_mobile.setToolTipText("Mobile");
				BackgroundText_HashMap.put("Mobile", "Mobile");
				field_mobile.setText(BackgroundText_HashMap.get("Mobile"));
				field_mobile.addFocusListener(this);
			}
			{
				field_email = new JTextField();
				getContentPane().add(field_email);
				field_email.setBounds(12, 251, 283, 27);
				field_email.setToolTipText("Email");
				BackgroundText_HashMap.put("Email", "Email");
				field_email.setText(BackgroundText_HashMap.get("Email"));
				field_email.addFocusListener(this);
			}
			{
				label_birthdate = new JLabel();
				getContentPane().add(label_birthdate);
				label_birthdate.setText("Birthdate");
				label_birthdate.setBounds(12, 292, 91, 28);
				label_birthdate.setFont(new java.awt.Font("Arial", 1, 12));

			}
			{
				field_birthday = new JTextField();
				getContentPane().add(field_birthday);
				field_birthday.setBounds(108, 292, 48, 28);
				field_birthday.setToolTipText("DD");
				BackgroundText_HashMap.put("DD", "DD");
				field_birthday.setText(BackgroundText_HashMap.get("DD"));
				field_birthday.addFocusListener(this);
			}
			{
				field_birthmonth = new JTextField();
				getContentPane().add(field_birthmonth);
				field_birthmonth.setBounds(168, 292, 43, 28);
				field_birthmonth.setToolTipText("MM");
				BackgroundText_HashMap.put("MM", "MM");
				field_birthmonth.setText(BackgroundText_HashMap.get("MM"));
				field_birthmonth.addFocusListener(this);
			}
			{
				field_birthyear = new JTextField();
				getContentPane().add(field_birthyear);
				field_birthyear.setBounds(229, 292, 66, 28);
				field_birthyear.setToolTipText("YYYY");
				BackgroundText_HashMap.put("YYYY", "YYYY");
				field_birthyear.setText(BackgroundText_HashMap.get("YYYY"));
				field_birthyear.addFocusListener(this);
			}
			{
				label_gender = new JLabel();
				getContentPane().add(label_gender);
				label_gender.setText("Gender");
				label_gender.setBounds(12, 334, 70, 21);
				label_gender.setFont(new Font("Arial", Font.BOLD, 12));
			}
			{
				radio_female = new JRadioButton();
				getContentPane().add(radio_female);
				radio_female.setText("Female");
				radio_female.setBounds(108, 332, 85, 26);
			}
			{
				radio_male = new JRadioButton();
				getContentPane().add(radio_male);
				radio_male.setText("Male");
				radio_male.setSelected(true);
				radio_male.setBounds(211, 334, 84, 24);
			}
			{

				ResultSet AllContacts = contactsRepo.readAllContacts();
				Object[] contactnames = contactsRepo.readContactNames(
						AllContacts).toArray();
				ComboBoxModel combo_customersModel = new DefaultComboBoxModel(
						contactnames);
				combo_customers = new JComboBox();
				getContentPane().add(combo_customers);
				getContentPane().add(getLabel_physicaladdress());
				getContentPane().add(getField_address1());
				getContentPane().add(getJTextField1());
				getContentPane().add(getJTextField1x());
				getContentPane().add(getField_postcode());
				getContentPane().add(getField_city());
				getContentPane().add(getField_state());
				getContentPane().add(getCombobox_countrys());
				getContentPane().add(getButton_save());
				getContentPane().add(getButton_cancle());
				combo_customers.setModel(combo_customersModel);
				combo_customers.setBounds(12, 371, 278, 26);
			}
			pack();
			this.setSize(620, 549);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private JLabel getLabel_physicaladdress() {
		if (label_physicaladdress == null) {
			label_physicaladdress = new JLabel();
			label_physicaladdress.setText("Physical address");
			label_physicaladdress.setBounds(332, 83, 179, 24);
			label_physicaladdress.setFont(new Font("Arial", Font.BOLD, 12));
		}
		return label_physicaladdress;
	}

	private JTextField getField_address1() {
		if (field_address1 == null) {
			field_address1 = new JTextField();
			field_address1.setBounds(332, 120, 262, 28);
			field_address1.setToolTipText("Address");
			BackgroundText_HashMap.put("Address", "Address");
			field_address1.setText(BackgroundText_HashMap.get("Address"));
			field_address1.addFocusListener(this);
		}
		return field_address1;
	}

	private JTextField getJTextField1() {
		if (field_address2 == null) {
			field_address2 = new JTextField();
			field_address2.setBounds(332, 165, 262, 28);
			field_address2.setToolTipText("Address");
			BackgroundText_HashMap.put("Address", "Address");
			field_address2.setText(BackgroundText_HashMap.get("Address"));
			field_address2.addFocusListener(this);
		}
		return field_address2;
	}

	private JTextField getJTextField1x() {
		if (field_address3 == null) {
			field_address3 = new JTextField();
			field_address3.setBounds(332, 209, 262, 28);
			field_address3.setToolTipText("Address");
			BackgroundText_HashMap.put("Address", "Address");
			field_address3.setText(BackgroundText_HashMap.get("Address"));
			field_address3.addFocusListener(this);
		}
		return field_address3;
	}

	private JTextField getField_postcode() {
		if (field_postcode == null) {
			field_postcode = new JTextField();
			field_postcode.setBounds(332, 250, 157, 28);
			field_postcode.setToolTipText("Post code");
			BackgroundText_HashMap.put("Post code", "Post code");
			field_postcode.setText(BackgroundText_HashMap.get("Post code"));
			field_postcode.addFocusListener(this);
		}
		return field_postcode;
	}

	private JTextField getField_city() {
		if (field_city == null) {
			field_city = new JTextField();
			field_city.setBounds(332, 290, 157, 26);
			field_city.setToolTipText("City");
			BackgroundText_HashMap.put("City", "City");
			field_city.setText(BackgroundText_HashMap.get("City"));
			field_city.addFocusListener(this);
		}
		return field_city;
	}

	private JTextField getField_state() {
		if (field_state == null) {
			field_state = new JTextField();
			field_state.setBounds(332, 331, 157, 25);
			field_state.setToolTipText("State");
			BackgroundText_HashMap.put("State", "State");
			field_state.setText(BackgroundText_HashMap.get("State"));
			field_state.addFocusListener(this);
		}
		return field_state;
	}

	private JComboBox getCombobox_countrys() {
		if (combobox_countrys == null) {
			ComboBoxModel combobox_countrysModel = new DefaultComboBoxModel(
					new String[] { "Item One", "Item Two" });
			combobox_countrys = new JComboBox();
			combobox_countrys.setModel(combobox_countrysModel);
			combobox_countrys.setBounds(332, 368, 255, 24);
		}
		return combobox_countrys;
	}

	private JButton getButton_save() {
		if (button_save == null) {
			button_save = new JButton();
			button_save.setIcon(new ImageIcon(getClass().getClassLoader()
					.getResource("com/vendhaq/layout/images/buttonsave.png")));
			button_save
					.setRolloverIcon(new ImageIcon(
							getClass()
									.getClassLoader()
									.getResource(
											"com/vendhaq/layout/images/buttonsave_rollover.png")));
			button_save.setBounds(469, 433, 115, 33);
			button_save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					saveContact();
					disposeScreen();
				}
			});
		}
		return button_save;
	}

	private JButton getButton_cancle() {
		if (button_cancle == null) {
			button_cancle = new JButton();
			button_cancle.setBounds(344, 433, 115, 33);
			button_cancle
					.setIcon(new ImageIcon(
							getClass()
									.getClassLoader()
									.getResource(
											"com/vendhaq/layout/images/buttoncancle.png")));
			button_cancle
					.setRolloverIcon(new ImageIcon(
							getClass()
									.getClassLoader()
									.getResource(
											"com/vendhaq/layout/images/buttoncancle_rollover.png")));
			button_cancle.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					disposeScreen();
				}
			});
		}
		return button_cancle;
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object source = e.getSource();
		if (source instanceof JTextField) {
			JTextField textfield = (JTextField) source;
			if (!textfield.getText().isEmpty()
					&& textfield.getText().equalsIgnoreCase(
							textfield.getToolTipText())) {
				textfield.setText("");
			}
			System.out.println(textfield.getText() + " Gained Focus");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();
		if (source instanceof JTextField) {
			JTextField textfield = (JTextField) source;
			System.out.println(textfield.getText() + " Gained Lost");
			if (textfield.getText().isEmpty()) {
				textfield.setText(BackgroundText_HashMap.get(textfield
						.getToolTipText()));
			}
		}
	}

	protected void disposeScreen() {
		this.dispose();
		this.show(false);

	}

	protected void saveContact() {
		/*read data from the */
		HashMap<String, String> contactdetails = readContactData();
		storeContactDetails(contactdetails);
		storeContactAddress(contactdetails);
		
		
		

	}

	private void storeContactAddress(HashMap<String, String> contactdetails) {
		// TODO Auto-generated method stub
		
	}

	private void storeContactDetails(HashMap<String, String> contactdetails) {
		/*store in vtiger_contactdetails*/
		String query = "select max(contactid) as maxid from vtiger_contactdetails";
		System.out.println("query:"+query);
		Connection conn= LocalDbConfiguration.getConnection();
		Statement stmt = null;
		int contactid = 0;
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				contactid = result.getInt("maxid");
				System.out.println("max contactid:"+contactid);
				contactid++;
				System.out.println("next contactid:"+contactid);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query = "INSERT INTO vtiger_contactdetails (contactid,contact_no,accountid,salutation,firstname,lastname,email,phone,mobile,title,department,fax,reportsto,training,usertype,contacttype,otheremail,yahooid,donotcall,emailoptout,imagename,reference,notify_owner) VALUES" +
		"("+contactid+",'conne12',0,'--None--','" +contactdetails.get("firstname")+ "','"+contactdetails.get("lastname")+"','"+contactdetails.get("email")+"','','" +contactdetails.get("mobile")+ "','','','',0,NULL,NULL,NULL,NULL,'',0,0,'',0,0)";
		System.out.println("query:"+query);
		try {
			boolean status = stmt.execute(query);
			System.out.println("Status: "+status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	private HashMap<String, String> readContactData() {

		HashMap<String, String> contactdetails = new HashMap<String, String>();
		contactdetails.put("firstname", this.field_firstname.getText().trim());
		contactdetails.put("lastname", this.field_lastname.getText().trim());
		contactdetails.put("company", this.field_company.getText().trim());
		contactdetails.put("customercode", this.field_customercode.getText()
				.trim());
		contactdetails.put("mobile", this.field_mobile.getText().trim());
		contactdetails.put("email", this.field_email.getText().trim());
		String birthday = this.field_birthday.getText().trim() + " - "
				+ this.field_birthmonth.getText().trim() + " - "
				+ this.field_birthyear.getText().trim();
		contactdetails.put("birthday", birthday);

		String sex = "";
		if (this.radio_male.isSelected()) {
			sex = "Male";
		} else if (this.radio_female.isSelected()) {
			sex = "Female";
		}

		contactdetails.put("sex", sex);
		contactdetails.put("customer", this.combo_customers.getSelectedItem()
				.toString().trim());

		String streetaddress = this.field_address1.getText().trim() + ", "
				+ this.field_address2.getText().trim() + ", "
				+ this.field_address3.getText().trim();
		contactdetails.put("streetaddress", streetaddress);

		contactdetails.put("postcode", this.field_postcode.getText().trim());
		contactdetails.put("city", this.field_city.getText().trim());
		contactdetails.put("state", this.field_state.getText().trim());
		contactdetails.put("country", this.combobox_countrys.getSelectedItem()
				.toString().trim());

		return contactdetails;
	}
}
