package model.entity;

public abstract class Utente {
	
	private String username;
	private String password;
	private String email;
	private String telefono;
	private String cellulare;
	
	/**
	 * Costruttore senza parametri
	 */
	public Utente() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.telefono = null;
		this.cellulare = null;
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
		if (!username.matches("^[a-zA-Z0-9]*$") // Username senza spazi e solo alfanumerico
				|| (username.length() < 6  && username.length() > 24))
			throw new IllegalArgumentException("Username non valido");
		
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
		/**
		 * 
		 */
		if ((!password.matches("\\d") && !password.matches("[A-Za-z]")) // La password contiene almeno un numero e una lettera
				|| (password.length() < 8 && password.length() > 64))
			throw new IllegalArgumentException("Password non valida");
		
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
		if (!email.matches("\\S+@\\S+\\.\\S+")) // Regex tollerante
			throw new IllegalArgumentException("E-Mail non valida");
			
		this.email = email;
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
		if (!telefono.matches("^[0-9]+$") && (telefono.length() < 9 && telefono.length() > 15))
			throw new IllegalArgumentException("Telefono non valido");
			
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
		if (!cellulare.matches("^[0-9]+$") && (cellulare.length() < 9 && cellulare.length() > 15))
			throw new IllegalArgumentException("Cellulare non valido");
		
		this.cellulare = cellulare;
	}

}
