import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	public static PreparedStatement psinsert, psdelete, psupdate, psselect;

	public static void dbConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava", "root", "mysql");
			psinsert = con.prepareStatement("insert into productinfo values(?,?,?,?)");
			psupdate = con.prepareStatement("update productinfo set name=?, price=?, quantity=? where pid=?");
			psdelete = con.prepareStatement("delete from productinfo where pid=?");
			psselect = con.prepareStatement("select * from productinfo where pid=?");
			System.out.println("Database connected...");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
