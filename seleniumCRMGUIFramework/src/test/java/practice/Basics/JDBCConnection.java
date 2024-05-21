package practice.Basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {

		// step1: load/register the database driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);

		// step2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("Connection Done");

		// step3: create SQL statement
		Statement stat = conn.createStatement();

		// step4: execute select query & get result
		ResultSet resultSet = stat.executeQuery("select * from project");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
					+ "\t" + resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getInt(6));
		}

		// step5: close the connection
		conn.close();
	}
}
