package practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class ProjectCheckInBackendTest {

	@Test
	public void projectCheck() throws SQLException {
		String expectedProj = "facebook";
		boolean flag = false;
		Connection conn = null;
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("====connection successful=====");
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery("select * from project");
			while (resultSet.next()) {
				String actualProj = resultSet.getString(4);
				if (expectedProj.equals(actualProj)) {
					flag = true;
					System.out.println(expectedProj+" is available");
				}
			}
			if (flag==false) {
				System.out.println(expectedProj+" is not available");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println("exception handled");
		}
		finally {
			conn.close();
			System.out.println("=====connection closed=====");
		}
	}
}
