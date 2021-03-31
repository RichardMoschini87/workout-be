package com.example.demo.rest.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.database.DbUtils;
import com.example.demo.integration.dao.UtenteDao;
import com.example.demo.integration.pojo.Utente;

@Path("/utenti")
public class Risorse {

	@Autowired
	private JdbcTemplate jdbc;

	@GET
	@Produces("application/json")
	@Path("/utente/{idUtente}")
	public Utente getUtente(@PathParam("idUtente") String idUtente) throws Exception {
		Utente utente;
		UtenteDao dao;
		Connection conn = null;

		if (idUtente == null || idUtente.isEmpty())
			throw new Exception("inserire il parametro nella query");

		conn = DbUtils.getConnection(jdbc);

		utente = new Utente();
		utente.setId(idUtente);

		dao = new UtenteDao(utente);

		utente = (Utente) dao.getObjectKey(conn);

		return utente;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@GET
	@Produces("application/json")
	@Path("/all")
	public List<Utente> getUtenti() throws SQLException {
		List<Utente> utenti = new ArrayList<>();
		Connection conn = DbUtils.getConnection(jdbc);

		UtenteDao dao = new UtenteDao(null);

		return dao.getResultList(conn);
	}
}