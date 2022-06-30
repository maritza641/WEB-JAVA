package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.OrdenTrab;

public interface OrtrabDAO {
	
	public List<OrdenTrab> findAllOrdenDeTrabajo() throws SQLException, NamingException;
	public OrdenTrab findOrdenDeTrabajoById(int otId) throws SQLException, NamingException;
	public void createOrdenDeTrabajo(OrdenTrab ot) throws SQLException, NamingException;
	public void deleteOrdenDeTrabajo(int otId)	throws SQLException, NamingException;
	public OrdenTrab findLastCreatedOrdenDeTrabajo() throws SQLException, NamingException;
	public void editOredenTrabajo(OrdenTrab ot) throws SQLException, NamingException;

}
