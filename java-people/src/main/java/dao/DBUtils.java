package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

	public static Connection getConexion() throws SQLException, NamingException {
		
		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/postgre");
			return dataSource.getConnection();
	}

}
