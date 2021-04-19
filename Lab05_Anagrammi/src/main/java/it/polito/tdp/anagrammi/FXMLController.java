package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;

import it.polito.tdp.anagrammi.model.*;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserimento;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private Button btnReset;

    @FXML
    void handleCalcola(ActionEvent event) {
    	String parola = this.txtInserimento.getText();
    	
    	if (parola.contains(" ")) {
    		this.txtCorretti.setText("Inserire una parola valida!");
    		return;
    	}
    	
    	List<String> corretti = new ArrayList<>();
    	List<String> errati = new ArrayList<>();
    	List<String> tutti = this.model.anagrammi(parola);
    	
    	for (String anagramma : tutti) {
    		if (this.model.isCorrect(anagramma)) {
    			corretti.add(anagramma);
    		}
    		else {
    			errati.add(anagramma);
    		}
    	}
    	
    	String corr = "";
    	for (String s : corretti) {
    		corr = corr + s + "\n";
    	}
    	this.txtCorretti.setText(corr);
    	
    	String err = "";
    	for (String s : errati) {
    		err = err + s + "\n";
    	}
    	this.txtErrati.setText(err);
    }

    @FXML
    void handleInserimento(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtCorretti.setText("");
    	this.txtErrati.setText("");
    	this.txtInserimento.setText("");
    }

    @FXML
    void initialize() {
        assert txtInserimento != null : "fx:id=\"txtInserimento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel (Model model) {
    	this.model=model;
    }
}
