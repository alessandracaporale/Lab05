package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import it.polito.tdp.anagrammi.model.*;

public class AnagrammaDAO {

	public List<Anagramma> getDizionario() {
		String sql = "SELECT * "
				+ "FROM parola";
		
		List<Anagramma> parole = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Anagramma p = new Anagramma (rs.getInt("id"), rs.getString("nome"));
				parole.add(p);
			}
			conn.close();
			rs.close();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException ("Eccezione in getDizionario", e);
		}
		return parole;
	}
	
	public boolean isCorrect (String anagramma) {
		boolean corretto = false;
		
		String sql = "SELECT p.id, p.nome "
				+ "FROM parola p "
				+ "WHERE p.nome = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Anagramma p = new Anagramma(rs.getInt("id"), rs.getString("nome"));
				if (p.getNome().equals(anagramma)) {
					corretto = true;
				}
			}
			conn.close();
			rs.close();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException ("Eccezione in isCorrect", e);
		}
		
		return corretto;
	}

}
