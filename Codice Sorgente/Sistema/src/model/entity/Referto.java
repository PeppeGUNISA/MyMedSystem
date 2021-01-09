/**
 * 
 */
package model.entity;

import java.io.File;
import java.util.Date;

/**
 * @author 
 *
 */
public class Referto {
	
	private String id;
	private String note;
	private File file;
	private String paziente;
	private String prestazione;
	private Date dataInserimento;
	private String laboratorio;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the paziente
	 */
	public String getPaziente() {
		return paziente;
	}
	/**
	 * @param paziente the paziente to set
	 */
	public void setPaziente(String paziente) {
		this.paziente = paziente;
	}
	/**
	 * @return the prestazione
	 */
	public String getPrestazione() {
		return prestazione;
	}
	/**
	 * @param prestazione the prestazione to set
	 */
	public void setPrestazione(String prestazione) {
		this.prestazione = prestazione;
	}
	/**
	 * @return the dataInserimento
	 */
	public Date getDataInserimento() {
		return dataInserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	/**
	 * @return the laboratorio
	 */
	public String getLaboratorio() {
		return laboratorio;
	}
	/**
	 * @param laboratorio the laboratorio to set
	 */
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	
}
