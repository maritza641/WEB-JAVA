package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Electrodomestico;
import modelo.OrdenTrab;

public class OrtrabDAOImp implements OrtrabDAO {

private ElectrodomesticosDAO electrodomesticosDAO;

	public OrtrabDAOImp(ElectrodomesticosDAO electrodomesticosDAO) {
		
		this.electrodomesticosDAO = electrodomesticosDAO;
	}
	
	@Override
	public List<OrdenTrab> findAllOrdenDeTrabajo() throws SQLException, NamingException {
		
		try (
				Connection conex = DBUtils.getConexion();
				Statement st 	 = conex.createStatement();
			) {
			
			String sql   = "SELECT * FROM ordentrabajp";
			ResultSet rs = st.executeQuery(sql);
			List<OrdenTrab> ordenesTrab = new ArrayList<>();
			while(rs.next()) {
				int id 					 = rs.getInt("id_ot");
				String estado 			 = rs.getString("estado");
				LocalDate fechaSolicitud = rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaActOrden  = rs.getObject("fechaactorden", LocalDate.class);
				int id_electrodomestico  = rs.getInt("id_electrodomestico");
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(id_electrodomestico);
				OrdenTrab ordenTrab = new OrdenTrab(id, estado, fechaSolicitud, fechaActOrden, electrodomestico);
					ordenesTrab.add(ordenTrab);
			}
			return ordenesTrab;	
		}
	}

	@Override
	public OrdenTrab findOrdenDeTrabajoById(int otId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM ordentrabajo WHERE id_ot = ?";
		try (
				Connection conex     = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, otId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 					 = rs.getInt("id_ot");
				String estado 			 = rs.getString("estado");
				LocalDate fechaSolicitud = rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaActOrden  = rs.getObject("fechaactorden", LocalDate.class);
				int id_electrodomestico  = rs.getInt("id_electrodomestico");
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(id_electrodomestico);
				return new OrdenTrab(id, estado, fechaSolicitud, fechaActOrden, electrodomestico);
			}
		}
		return null;
	}

	@Override
	public void createOrdenDeTrabajo(OrdenTrab ot) throws SQLException, NamingException {
		
		String sql = "INSERT INTO ordentrabajo(estado, fechasolicitud, fechaactorden, id_electrodomestico) VALUES (?, ?, ?, ?)";
		try (
				Connection conex     = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setString(1, ot.getEstado());
			ps.setObject(2, ot.getFechaSolicitud());
			ps.setObject(3, ot.getFechaActOrden());
			ps.setInt(4, ot.getElectrodomestico_id().getId());
			ps.executeUpdate();
		}
	}
		
	public void editOrdenDeTrabajo(OrdenTrab ot) throws SQLException, NamingException {
		
		String sql = "UPDATE ordentrabajo SET fechaactorden = ?, estado = ? WHERE id_ot = ?";
		try (
				Connection conex     = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setObject(1, LocalDate.now());
			ps.setString(2, ot.getEstado());
			ps.setInt(3, ot.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void deleteOrdenDeTrabajo(int otId) throws SQLException, NamingException {
		
		String sql = "DELETE FROM ordentrabajo WHERE id_ot = ?";
		try (
				Connection conex     = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, otId);
			ps.executeUpdate();
		}
	}

	@Override
	public OrdenTrab findLastCreatedOrdenDeTrabajo() throws SQLException, NamingException {
		
		return null;
	}

	@Override
	public void editOredenTrabajo(OrdenTrab ot) throws SQLException, NamingException {
		// TODO Auto-generated method stub
		
	}

}
