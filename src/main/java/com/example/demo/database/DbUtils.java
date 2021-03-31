package com.example.demo.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DbUtils {

	/**
	 * Restituisce una connessione come da impostazioni spring
	 * 
	 * @param jdbc
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(JdbcTemplate jdbc) throws SQLException {
		Connection conn = null;
		DataSource data;

		data = jdbc.getDataSource();
		conn = data.getConnection();

		return conn;

	}
}
