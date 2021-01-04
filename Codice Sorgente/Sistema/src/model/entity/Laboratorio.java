/**
 * 
 */
package model.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 
 *
 */
public class Laboratorio extends Utente {
	
	public enum Giorno { Lunedì, Martedì, Mercoledì, Giovedì, Venerdì, Sabato, Domenica }
	
	//TODO: implementare controlli, converrebbe avere due colonne per orario apertura e chiusura
	private String pIva;
	private String denominazione;
	private String citta;
	private String provincia;
	private String cap;
	private List<Giorno> giorniApertura;
	private LocalTime orarioApertura;
	private LocalTime orarioChiusura;
	
	public Laboratorio() {
		super();
		setRuolo(Utente.Ruolo.laboratorio);
	}
	
	/**
	 * @return the pIva
	 */
	public String getpIva() {
		return pIva;
	}
	/**
	 * @param pIva the pIva to set
	 */
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}
	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}
	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}
	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}
	/**
	 * @return the giorniApertura
	 */
	public List<Giorno> getGiorniApertura() {
		return giorniApertura;
	}
	
	public String getGiorniAsString() {
		String stringa = "";
		Giorno[] giorni = Giorno.values();
		for (Giorno giorno : giorni) {
			if (giorniApertura.contains(giorno))
				stringa = stringa + "1";
			else
				stringa = stringa + "0";
		}
		return stringa;
	}
	
	/**
	 * @param giorniApertura the giorniApertura to set
	 */
	public void setGiorniApertura(List<Giorno> giorniApertura) {
		Collections.sort(giorniApertura);
		this.giorniApertura = giorniApertura;
	}
	/**
	 * @return the orarioApertura
	 */
	public LocalTime getOrarioApertura() {
		return orarioApertura;
	}
	/**
	 * @param orarioApertura the orarioApertura to set
	 */
	public void setOrarioApertura(LocalTime orarioApertura) {
		this.orarioApertura = orarioApertura;
	}
	/**
	 * @return the orarioChiusura
	 */
	public LocalTime getOrarioChiusura() {
		return orarioChiusura;
	}
	/**
	 * @param orarioChiusura the orarioChiusura to set
	 */
	public void setOrarioChiusura(LocalTime orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}
	
	public static List<Giorno> stringAsGiorni(String stringa) {
		List<Giorno> giorni = new ArrayList<>();
		char[] caratteri = stringa.toCharArray();
		for (Giorno giorno : Giorno.values()) {
			if (caratteri[giorno.ordinal()] == '1')
				giorni.add(giorno);
		}
		return giorni;
	}
	
}
