package model.entity;


/**
 * 
 * @author anna
 *
 */

public class Paziente extends Utente{
	
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String luogoNascita;
	private GregorianCalendar dataNascita;
	private String stato;
	private String città;
	private String provincia;
	private int cap;
	private String indirizzo;
	
	/*
	 * Costruttore senza parametri
	 */
	
	public Paziente() {
		
		super();
		this.nome = null;
		this.cognome = null;
		this.codiceFiscale = null;
		this.luogoNascita = null;
		this.dataNascita =  null;
		this.stato = null;
		this.città = null;
		this.provincia = null;
		this.cap = null;
		this.indirizzo = null;
	}
	
	
	/*
	 * Costruttore
	 */
	
	public Paziente(String username, String password, String email, String telefono, String cellulare, String nome, String cognome, String codiceFiscale, 
			        String luogoNascita, GregorianCalendar dataNascita, String stato, String città, String provincia, int cap, String indirizzo) {
		
		super(username, password, email, telefono, cellulare, Ruolo.paziente);
		setNome(nome);
		setCognome(cognome);
		setCodiceFiscale(codiceFiscale);
		setLuogoNascita(luogoNascita);
		setDataNascita(dataNascita);
		setStato(stato);
		setCittà(città);
		setProvincia(provincia);
		setCap(cap);
		setIndirizzo(indirizzo);
	}
	
	/*
	 * @return nome
	 */
	
	public String getNome() {
		
		return nome;
	}
	
	/*
	 * @param nome : nome da impostare
	 */
	
	public void setNome(String nome) {
		
		if (nome.matches("\\d+")) //Se c'Ã¨ almeno un numero
			throw new IllegalArgumentException("Nome non valido");
		this.nome = nome;
	}
	
	/*
	 * @return cognome
	 */
	
	public String getCognome() {
		
		return cognome;
	}
	
	/*
	 * @param cognome : cognome da impostare
	 */
	
	public void setCognome(String cogome) {
		
		if (cognome.matches("\\d+")) //Se c'Ã¨ almeno un numero
			throw new IllegalArgumentException("Cognome non valido");
		this.cognome = cognome;
	}
	
	/*
	 * @return codice fiscale
	 */
	
	public String getCodiceFiscale() {
		
		return codiceFiscale;
	}
	
	/*
	 * @param codiceFiscale : codice fiscale da impostare
	 */
	
	public void setCodiceFiscale(String codiceFiscale) {
		
		if (!codiceFiscale.matches("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$"))
			throw new IllegalArgumentException("Codice fiscale non valido");
		this.codiceFiscale = codiceFiscale;
		
	}
	
	/*
	 * @return luogoNascita
	 */
	
	public String getLuogoNascita() {
		
		return luogoNascita;
	}
	
	/*
	 * @param luogoNascita : luogo di nascita da impostare
	 */
	
	public void setLuogoNascita(String luogoNascita) {
		
		if(!luogoNascita.matches("^[A-Za-z- ]+$"))
			throw new IllegalArgumentException("Luogo di nascita non valido");
		this.luogoNascita = luogoNascita;
	}
	
	/*
	 * @return dataNascita
	 */
	
	public GregorianCalendar dataNascita() {
		
		return dataNascita;
	}
	
	/*
	 * @param dataNascita : data di nascita da impostare
	 */
	
	public void setDataNascita(GregorianCalendar dataNascita) {
		
		if(!dataNascita.matches("‘[^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$]’"))
			throw new IllegalArgumentException("Data si nascita non valida");
		this.dataNascita = dataNascita;
	}
	
	/*
	 * @return stato
	 */
	
	public String getStato() {
		
		return stato;
	}
	
	/*
	 * @param stato : stato da impostare
	 */
	
	public void setStato(String stato) {
		
		if(!stato.matches("(‘^[A-Za-z- ]+$’]"))
			throw new IllegalArgumentException("Stato non valido");
		this.stato = stato;
	}
	
	/*
	 * @return città
	 */
	
	public String getCittà() {
		
		return città;
	}
	
	/*
	 * @param città : città da impostare
	 */
	
	public void setCittà(String città) {
		
		if(!città.matches("(‘^[A-Za-z- ]+$’]"))
			throw new IllegalArgumentException("Città non valida");
		this.città = città;
	}
	
	/*
	 * @return provincia
	 */
	
	public String getProvincia() {
		
		return provincia;
	}
	
	/*
	 * @param provincia : provincia da impostare
	 */
	
	public void setProvincia(String provincia) {
		
		if(!provincia.matches("(‘^[A-Za-z- ]+$’]"))
			throw new IllegalArgumentException("Provincia non valida");
		this.provincia = provincia;
	}
	
	/*
	 * @return cap
	 */
	
	public int getCap() {
		
		return cap;
	}
	
	/*
	 * @param cap : cap da impostare
	 */
	
	public void setCap(int cap) {
		
		if(!cap.matches("‘^[0-9]+$’"))
			throw new IllegalArgumentException("Cap non valido");
		this.cap = cap;
	}
	
	/*
	 * @return indirizzo
	 */
	
	public String getIndirizzo() {
		
		return indirizzo;
	}
	
	/*
	 * @param indirizzo : indirizzo da impostare
	 */
	
	public void setIndirizzo(String indirizzo) {
		
		if(!indirizzo.matches("(‘^[A-Za-z- ]+$’]"))
			throw new IllegalArgumentException("Indirizzo non valido");
		this.indirizzo = indirizzo;
	}
	}


