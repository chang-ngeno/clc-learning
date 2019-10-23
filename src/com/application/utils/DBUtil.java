package com.application.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private String driver = "com.mysql.cl.jdbc.Driver";
	private String dbname = "world";
	private String username = "root";
	private String passwd = "admin";
	private String host = "localhost";
	private int port = 3306;
	private Connection conn = null;

	public Connection getConnection() {
		System.out.println("Getting DB connection");
		if (conn != null)
			return conn;
		else
			try {
				Class.forName(driver);
				System.out.println("URL is => 'jdbc:mariadb://" + host + ":" + port + "/" + dbname + "'");
				conn = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" + dbname, username, passwd);
				System.out.println(conn.toString());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return conn;

	}

	public void closeConnection() {
		System.out.println("Closing DB Connection");
		try {
			// stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DBUtil(String dbname, String username, String passwd, String host, int port, Connection conn) {
		super();
		this.dbname = dbname;
		this.username = username;
		this.passwd = passwd;
		this.host = host;
		this.port = port;
		this.conn = conn;
	}

	public DBUtil() {
		super();
	}

	public void readProps() {
		String filePath = "WebContent/WEB-INF/dbconfig.properties";
		Properties prop = new Properties();

		try (InputStream inputStream = new FileInputStream(filePath)) {

			// Loading the properties.
			prop.load(inputStream);

			// Getting properties
			host = prop.getProperty("database.host");
			username = prop.getProperty("database.username");
			passwd = prop.getProperty("database.password");
			driver = prop.getProperty("database.driver");
			dbname = prop.getProperty("database.dbname");
			port = Integer.parseInt(prop.getProperty("database.port"));

		} catch (IOException ex) {
			System.out.println("Problem occured when reading file !");
			ex.printStackTrace();
		}
	}

}
