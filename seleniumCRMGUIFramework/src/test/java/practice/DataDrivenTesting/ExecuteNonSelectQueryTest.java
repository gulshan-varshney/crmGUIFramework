package practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQueryTest {

	public static void main(String[] args) throws SQLException {

		// fetching current date
		Date date = new Date();

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = simpleDate.format(date);

		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement stat = conn.createStatement();
		int result = stat.executeUpdate(
				"INSERT INTO PROJECT VALUES ('TY_PROJ_005', 'GULSHAN', '" + currentDate + "', 'facebook4')");
		System.out.println(result);
		conn.close();

	}
}
