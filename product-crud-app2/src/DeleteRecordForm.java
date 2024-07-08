import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteRecordForm {
	JFrame fr = new JFrame("Delete Record");
	JLabel la = new JLabel("Enter Product Id:");
	JTextField tb = new JTextField();
	JButton bt = new JButton("Delete Record");
	PreparedStatement ps;

	public DeleteRecordForm() {
		fr.setSize(500, 300);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		ps=Database.psdelete;
		addComponents();
	}

	private void addComponents() {
		fr.setLayout(null);

		Font fo = new Font("arial", 0, 19);

		la.setBounds(20, 80, 200, 30);
		la.setFont(fo);
		fr.add(la);

		tb.setBounds(220, 80, 250, 30);
		tb.setFont(fo);
		fr.add(tb);

		bt.setBounds(150, 150, 150, 30);
		fr.add(bt);
		bt.addActionListener(new DeleteListener());
	}

	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ps.setString(1, tb.getText());
				int n = ps.executeUpdate();
				JOptionPane.showMessageDialog(fr, "Product Record deleted...");
				System.out.println("Product record deleted successfully...");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}

	public static void main(String[] args) {
		new DeleteRecordForm();
	}

}
