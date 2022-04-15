package FamilyBudget.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	public static Connection getConnection() throws Exception{
		String dbRoot= "jdbc:mysql://";
		String hostName="localhost:3306/";
		String dbName="familybase";
		String dbUrl = dbRoot+hostName+dbName;
		
		String hostUsername="root";
		String hostPassword="";
				
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection myConn = (Connection)DriverManager.getConnection(dbUrl, hostUsername, hostPassword);
		
		return myConn;
	}
	
}
