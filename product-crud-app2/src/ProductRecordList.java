import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductRecordList {
	JFrame fr = new JFrame("Product Record List");
	JTable ta;
	DefaultTableModel model=new DefaultTableModel();

	public ProductRecordList() {
		fr.setSize(500, 500);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		ta=new JTable(model);
		JScrollPane pa = new JScrollPane(ta);
		fr.add(pa);
		showData();
	}
	
	private void showData() {
		model.addColumn("Product Id");
		model.addColumn("Product Name");
		model.addColumn("Product Price");
		model.addColumn("Product Quantity");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava", "root", "mysql");
			Statement st=con.createStatement();
			ResultSet rst=st.executeQuery("select * from productinfo");
			while(rst.next()) {
				Object [] data= {rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)};
				model.addRow(data);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		new ProductRecordList();
	}

}
