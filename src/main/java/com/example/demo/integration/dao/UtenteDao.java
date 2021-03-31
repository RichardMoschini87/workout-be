package com.example.demo.integration.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.integration.pojo.Utente;

public class UtenteDao extends Dao {
	private Utente utente;

	public UtenteDao(Utente utente) {
		this.utente = utente;
	}

	@Override
	protected String getSqlKey() {
		return "select * from utenti where idutente = ?";
	}

	@Override
	protected String getSqlWhere() {
		return "select * from utenti";
	}

	@Override
	protected void setStatementKey(PreparedStatement pst) throws SQLException {
		int index = 1;

		if (utente.getId() != null)
			pst.setString(index, utente.getId());
	}

	@Override
	protected void setStatementWhere(PreparedStatement pst) {
     //
	}

	@Override
	protected Object getResultSet(ResultSet rs) throws SQLException {
		Utente obj = new Utente();

		obj.setId(rs.getString("idutente"));
		obj.setEmail(rs.getString("email"));
		obj.setNome(rs.getString("nome"));
		obj.setPassword(rs.getString("password"));

		return obj;
	}

}
