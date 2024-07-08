import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateRecordForm {
	JFrame fr = new JFrame("Update Record");
	JLabel la = new JLabel("Enter Product Id:");
	JTextField tb = new JTextField();
	JButton bt = new JButton("Get Record");
	PreparedStatement ps, ps1;
	JPanel pa = new JPanel();
	JLabel[] la1 = new JLabel[3];
	JTextField[] tb1 = new JTextField[3];
	JButton bt1 = new JButton("Update Record");

	public UpdateRecordForm() {
		fr.setSize(500, 550);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		ps=Database.psselect;
		ps1=Database.psupdate;
		addComponents();
	}

	private void addComponents() {
		fr.setLayout(null);

		Font fo = new Font("arial", 0, 19);

		la.setBounds(20, 50, 200, 30);
		la.setFont(fo);
		fr.add(la);

		tb.setBounds(220, 50, 250, 30);
		tb.setFont(fo);
		fr.add(tb);

		bt.setBounds(150, 100, 150, 30);
		fr.add(bt);
		bt.addActionListener(new GetListener());
		pa.setBounds(0, 150, 480, 350);
		fr.add(pa);
		pa.setVisible(false);
		addEditPanel();
	}

	private void addEditPanel() {
		pa.setLayout(null);
		int y = 50;
		String[] arr = { "Name", "Price", "Quantity" };
		Font fo = new Font("arial", 0, 19);
		for (int i = 0; i < 3; i++) {
			la1[i] = new JLabel("Edit Product " + arr[i]);
			la1[i].setBounds(20, y, 200, 30);
			la1[i].setFont(fo);
			pa.add(la1[i]);
			tb1[i] = new JTextField();
			tb1[i].setBounds(220, y, 250, 30);
			tb1[i].setFont(fo);
			pa.add(tb1[i]);
			y += 70;
		}
		bt1.setBounds(175, 250, 150, 30);
		pa.add(bt1);
		bt1.addActionListener(new UpdateListener());
	}

	class GetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ps.setString(1, tb.getText());
				ResultSet rst = ps.executeQuery();
				if (rst.next()) {
					pa.setVisible(true);
					tb1[0].setText(rst.getString(2));
					tb1[1].setText(rst.getString(3));
					tb1[2].setText(rst.getString(4));
				} else {
					JOptionPane.showMessageDialog(fr, "Product Record does not exist...");
					pa.setVisible(false);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ps1.setString(1, tb1[0].getText());
				ps1.setString(2, tb1[1].getText());
				ps1.setString(3, tb1[2].getText());
				ps1.setString(4, tb.getText());
				ps1.executeUpdate();
				JOptionPane.showMessageDialog(fr, "Product Record updated successfully...");
				pa.setVisible(false);

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	public static void main(String[] args) {
		new UpdateRecordForm();
	}

}
