package com.example.demo.integration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao {

	protected abstract String getSqlKey();

	protected abstract String getSqlWhere();

	protected abstract void setStatementKey(PreparedStatement pst) throws SQLException;

	protected abstract void setStatementWhere(PreparedStatement pst);

	protected abstract Object getResultSet(ResultSet rs) throws SQLException;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getResultList(Connection conn) throws SQLException {
		ResultSet resulset = null;
		ArrayList list = new ArrayList<>();

		String query = this.getSqlWhere();
		PreparedStatement pst = conn.prepareStatement(query);

		try {
			this.setStatementWhere(pst);
			resulset = pst.executeQuery();

			while (resulset.next()) {
				Object obj = this.getResultSet(resulset);
				list.add(obj);
			}

			return list;
		} finally {

			if (pst != null)
				pst.close();
			if (resulset != null)
				resulset.close();
		}
	}

	public Object getObjectKey(Connection conn) throws SQLException {
		Object obj = null;
		ResultSet resulset = null;

		String query = this.getSqlKey();
		PreparedStatement pst = conn.prepareStatement(query);

		try {
			this.setStatementKey(pst);
			resulset = pst.executeQuery();

			if (resulset.next())
				obj = this.getResultSet(resulset);

		} finally {
			if (pst != null)
				pst.close();
			if (resulset != null)
				resulset.close();
		}

		return obj;
	}
}
