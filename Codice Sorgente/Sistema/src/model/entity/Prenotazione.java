package model.entity;

/*
 * @author anna
 */

public class Prenotazione {
	
	private int id;
	private String luogo;
	private GregorianCalendar date;
	private Time orario;
	private String stato;
	
	/*
	 * Costruttore senza paramatri
	 */

	public Prenotazione() {
		
		id = 0;
		luogo = null;
		date = null;
		orario = null;
		stato = null;
	}
	
 /*
  * Costruttore
  */
	
	public Prenotazione(int id, String luogo, GregorianCalendar date, Time orario, String stato) {
		
		setId(id);
		setLuogo(luogo);
		setDate(date);
		setOrario(orario);
		setStato(stato);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(!id.matches("^[0-9]+$"))
			throw new IllegalArgumentException("Id non valido");
		this.id = id;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		if (!luogo.matches("^[A-Za-z- ]+$"))
			throw new IllegalArgumentException("Luogo non valido");
		this.luogo = luogo;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		GregorianCalendar oggi = new GregorianCalendar();
		if (date.after(oggi))
			throw new IllegalArgumentException("Data non valida");
		this.date = date;
	}

	public Time getOrario() {
		return orario;
	}

	public void setOrario(Time orario) {
		if(orario.matches("^(([0-1]?[0-9])|(2[0-4]))(:|\\.)[0-6]?[0-9](:|\\.)[0-6]?[0-9]$"))
			throw new IllegalArgumentException("Orario non valido");
		this.orario = orario;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
