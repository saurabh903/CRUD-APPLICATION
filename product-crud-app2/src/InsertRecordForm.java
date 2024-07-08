import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InsertRecordForm {
	JFrame fr =new JFrame("Insert Record");
	JLabel [] la=new JLabel[4];
	JTextField [] tb=new JTextField[4];
	JButton bt=new JButton("Save Record");
	PreparedStatement ps;
	public InsertRecordForm() {
		fr.setSize(500,500);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		ps=Database.psinsert;
		addComponents();
	}
	
	private void addComponents() {
		fr.setLayout(null);
		int y=50;
		String []arr= {"Id", "Name", "Price", "Quantity"};
		Font fo= new Font("arial", 0, 19);
		for(int i=0; i<4;i++) {
			la[i] = new  JLabel("Enter Product " +arr[i]);
			la[i].setBounds(20, y, 200, 30);
			la[i].setFont(fo);
			fr.add(la[i]);
			tb[i]=new JTextField();
			tb[i].setBounds(220, y, 250, 30);
			tb[i].setFont(fo);
			fr.add(tb[i]);
			y+=70;
		}
		bt.setBounds(175, 350, 150, 30);
		fr.add(bt);
		bt.addActionListener(new SaveListener());
	}
	
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String pid=tb[0].getText();
			String name=tb[1].getText();
			String price=tb[2].getText();
			String qty=tb[3].getText();
			try {
				ps.setString(1,pid);
				ps.setString(2,name);
				ps.setString(3,price);
				ps.setString(4,qty);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(fr, "Product Record saved...");
				System.out.println("Product record saved successfully...");
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
	}

	public static void main(String[] args) {
		new InsertRecordForm();
	}

}
