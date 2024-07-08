import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainClass {
	JFrame fr = new JFrame("Product Information System");
	JButton[] bt = new JButton[5];

	InsertRecordForm insert;
	ProductRecordList list;
	UpdateRecordForm update;
	DeleteRecordForm delete;
	GetSingleRecord search;

	public MainClass() {
		fr.setSize(500, 500);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		fr.getContentPane().setBackground(Color.cyan);
		fr.setLayout(null);
		addButtons();
		Database.dbConnection();
		insert = new InsertRecordForm();
		list = new ProductRecordList();
		update = new UpdateRecordForm();
		delete = new DeleteRecordForm();
		search = new GetSingleRecord();
		fr.setVisible(true);
	}

	private void addButtons() {
		int y = 70;
		String[] str = { "Insert", "Show", "Update", "Delete", "Search" };
		Font font = new Font("elephant", 0, 19);
		ProductListener listener = new ProductListener();
		for (int i = 0; i < 5; i++) {
			bt[i] = new JButton(str[i] + " Record");
			bt[i].setBounds(130, y, 220, 40);
			bt[i].setFont(font);
			bt[i].setBackground(Color.yellow);
			bt[i].addActionListener(listener);
			fr.add(bt[i]);
			y += 70;
		}
		bt[1].setText("Show Records List");
	}

	class ProductListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			JButton btn = (JButton) evt.getSource();
			if (btn == bt[0]) // On Insert Button click
				insert.fr.setVisible(true);
			else if (btn == bt[1]) // On Show Record Button click
				list.fr.setVisible(true);
			else if (btn == bt[2]) // On Update Button click
				update.fr.setVisible(true);
			else if (btn == bt[3]) // On Delete Button click
				delete.fr.setVisible(true);
			else if (btn == bt[4]) // On Search Button click
				search.fr.setVisible(true);

		}

	}

	public static void main(String[] args) {
		new MainClass();
	}

}
