/**
 * 
 */
package model.entity;

import java.util.GregorianCalendar;

/**
 * @author
 *
 */
public class Prenotazione {

	private int id;
	private String luogo;
	private GregorianCalendar dataOrario;
	private String username;
	private String prestazioneId;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the luogo
	 */
	public String getLuogo() {
		return luogo;
	}

	/**
	 * @param luogo the luogo to set
	 */
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	/**
	 * @return the dataOrario
	 */
	public GregorianCalendar getDataOrario() {
		return dataOrario;
	}

	/**
	 * @param dataOrario the dataOrario to set
	 */
	public void setDataOrario(GregorianCalendar dataOrario) {
		this.dataOrario = dataOrario;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the prestazioneId
	 */
	public String getPrestazioneId() {
		return prestazioneId;
	}

	/**
	 * @param prestazioneId the prestazioneId to set
	 */
	public void setPrestazioneId(String prestazioneId) {
		this.prestazioneId = prestazioneId;
	}

}
