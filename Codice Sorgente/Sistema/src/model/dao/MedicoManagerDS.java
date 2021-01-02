/**
 * 
 */
package model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Medico;
import model.entity.Paziente;

/**
 * @author 
 *
 */
public class MedicoManagerDS implements MedicoManager {

	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/mymedsystem");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	@Override
	public Medico getMedico(Paziente paziente) {
		// TODO Auto-generated method stub
		return null;
	}

}
