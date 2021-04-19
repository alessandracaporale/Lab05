package it.polito.tdp.anagrammi.model;

import it.polito.tdp.anagrammi.db.*;
import java.util.*;

public class Model {
	
	List<String> risultato;
	
	AnagrammaDAO adao = new AnagrammaDAO();

	public List<Anagramma> getDizionario() {
		return adao.getDizionario();
	}
	
	public boolean isCorrect (String anagramma) {
		return adao.isCorrect(anagramma);
	}
	
	public List<String> anagrammi (String parola) {
		risultato = new ArrayList<>();
		permuta("", parola, 0, risultato);
		
		return risultato;
	}
	
	public void permuta (String parziale, String lettere, int livello, List<String> risultato) {
		if (lettere.length()==0) {
			risultato.add(parziale);
		}
		else {
			for (int pos=0; pos<lettere.length(); pos++) {
				char tentativo = lettere.charAt(pos);
				
				String nuovaParziale = parziale + tentativo;
				String nuovaLettere = lettere.substring(0, pos)+lettere.substring(pos+1);
				
				permuta (nuovaParziale, nuovaLettere, livello+1, risultato);
			}
		}
	}
	
}
