package model.entity;

public class OperatoreASL extends Utente {
	
	private String nome;
	private String cognome;
	private String codiceFiscale;

	public OperatoreASL()
	{
		super();
		this.nome = null;
		this.cognome = null;
		this.codiceFiscale = null;
	}
	
	public OperatoreASL(String username, String password, String email, String telefono, String cellulare, String nome, String cognome) {
		super(username, password, email, telefono, cellulare, Ruolo.operatoreAsl);
		setNome(nome);
		setCognome(cognome);
		setCodiceFiscale(codiceFiscale);
	}

	/**
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome nome da impostare
	 */
	public void setNome(String nome) {
		if (nome.matches("\\d+")) //Se c'è almeno un numero
			throw new IllegalArgumentException("Nome non valido");
		this.nome = nome;
	}

	/**
	 * @return il cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome cognome da impostare
	 */
	public void setCognome(String cognome) {
		if (cognome.matches("\\d+"))//Se c'è almeno un numero
			throw new IllegalArgumentException("Cognome non valido");
		this.cognome = cognome;
	}

	/**
	 * @return il codice fiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale codiceFiscale da impostare
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		if (!codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$"))
			throw new IllegalArgumentException("Codice fiscale non valido");
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString() {
		return "OperatoreASL [nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
				+ ", getRuolo()=" + getRuolo() + ", getTelefono()=" + getTelefono() + ", getCellulare()="
				+ getCellulare() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
