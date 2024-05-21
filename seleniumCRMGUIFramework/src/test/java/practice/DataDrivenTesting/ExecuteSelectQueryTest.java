package practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("=====connection successful=====");
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery("select * from project");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
						+ resultSet.getString(3) + "\t" + resultSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("exception handled");
		} finally {
			conn.close();
			System.out.println("=====connection closed=====");
		}

	}
}
