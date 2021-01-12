package model.entity;

/**
 * 
 * @author cristian
 *
 */

public abstract class Utente {
	
	public static enum Ruolo { paziente, medico, laboratorio, operatoreAsl };
	
	private String username;
	private String password;
	private String email;
	private String telefono;
	private String cellulare;
	private Ruolo ruolo;
	
	/**
	 * Costruttore senza parametri
	 */
	public Utente() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.telefono = null;
		this.cellulare = null;
		this.ruolo = null;
	}
	
	/**
	 * Costruisce un utente
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param telefono
	 * @param cellulare
	 * @param ruolo
	 */
	public Utente(String username, String password, String email, String telefono, String cellulare, Ruolo ruolo) {
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setTelefono(telefono);
		setCellulare(cellulare);
		this.ruolo = ruolo;
	}
	
	/**
	 * @return lo username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username username da impostare
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password password da impostare
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return la email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email email da impostare
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return il ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * @return numero di telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono telefono da impostare
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return numero di cellulare
	 */
	public String getCellulare() {
		return cellulare;
	}

	/**
	 * @param cellulare cellulare da impostare
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", email=" + email + ", telefono=" + telefono
				+ ", cellulare=" + cellulare + ", ruolo=" + ruolo + "]";
	}

}
