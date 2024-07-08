import java.awt.Color;
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

public class GetSingleRecord {
	JFrame fr = new JFrame("Update Record");
	JLabel la = new JLabel("Enter Product Id:");
	JTextField tb = new JTextField();
	JButton bt = new JButton("Show Record");
	PreparedStatement ps;
	JPanel pa = new JPanel();
	JLabel[] la1 = new JLabel[3];
	JLabel[] la2 = new JLabel[3];

	public GetSingleRecord() {
		fr.setSize(500, 550);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		ps=Database.psselect;
		addComponents();
	}

	private void addComponents() {
		fr.setLayout(null);

		Font fo = new Font("arial", 0, 19);

		la.setBounds(50, 50, 200, 30);
		la.setFont(fo);
		fr.add(la);

		tb.setBounds(200, 50, 250, 30);
		tb.setFont(fo);
		fr.add(tb);

		bt.setBounds(170, 120, 150, 30);
		fr.add(bt);
		bt.addActionListener(new GetRecordListener());
		pa.setBounds(80, 150, 450, 350);
		fr.add(pa);
		pa.setVisible(false);
		addShowPanel();
	}

	private void addShowPanel() {
		pa.setLayout(null);
		int y = 50;
		String[] arr = { "Name", "Price", "Quantity" };
		Font fo = new Font("arial", Font.BOLD, 19);
		for (int i = 0; i < 3; i++) {
			la1[i] = new JLabel("Product " + arr[i]);
			la1[i].setBounds(20, y, 200, 30);
			la1[i].setFont(fo);
			pa.add(la1[i]);
			la2[i] = new JLabel();
			la2[i].setForeground(Color.magenta);
			la2[i].setBounds(220, y, 250, 30);
			la2[i].setFont(fo);
			pa.add(la2[i]);
			y += 70;
		}
		
	}

	class GetRecordListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ps.setString(1, tb.getText());
				ResultSet rst = ps.executeQuery();
				if (rst.next()) {
					pa.setVisible(true);
					la2[0].setText(rst.getString(2));
					la2[1].setText(rst.getString(3));
					la2[2].setText(rst.getString(4));
				} else {
					JOptionPane.showMessageDialog(fr, "Product Record does not exist...");
					pa.setVisible(false);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}


	public static void main(String[] args) {
		new GetSingleRecord();
	}

}
