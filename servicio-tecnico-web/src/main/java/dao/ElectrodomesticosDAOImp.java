package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Cliente;
import modelo.Electrodomestico;

public class ElectrodomesticosDAOImp implements ElectrodomesticosDAO {

	private ClienteDAO clientesDAO;

	public ElectrodomesticosDAOImp(ClienteDAO clienteDAO) {
		this.clientesDAO = clienteDAO;
	}
	
	@Override
	public List<Electrodomestico> findAllElectrodomesticos() throws SQLException, NamingException {
		
		try (
				Connection conex = DBUtils.getConexion();
				Statement st 	 = conex.createStatement();
			) {
			
			String sql   = "SELECT * FROM electrodomestico";
			ResultSet rs = st.executeQuery(sql);
			List<Electrodomestico> electrodomesticos = new ArrayList<>();
			while(rs.next()) {
				int id 			= rs.getInt("id_electrodomestico");
				String tipo 	= rs.getString("tipo");
				String problema = rs.getString("problema");
				int idCliente 	= rs.getInt("id_cliente");
				Cliente cliente = clientesDAO.findClienteById(idCliente);
				Electrodomestico electrodomestico = new Electrodomestico(id, tipo, problema, cliente);
				electrodomesticos.add(electrodomestico);
			}
			return electrodomesticos;
		}
	}
	
	@Override
	public Electrodomestico findElectrodomesticoById(int electrodomesticoId) throws SQLException, NamingException {
		
		String sql = "SELEC * FROM electrodomestico WHERE id_electrodomestico = ?";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, electrodomesticoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 			= rs.getInt("id_electrodomestico");
				String tipo 	= rs.getString("tipo");
				String problema = rs.getString("problema");
				int idCliente 	= rs.getInt("id_cliente");
				Cliente cliente = clientesDAO.findClienteById(idCliente);
				
				return new Electrodomestico(id, tipo, problema, cliente);
			}
		}
		return null;
	}

	@Override
	public void createElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {
		
		String sql = "INSERT INTO electrodomestico(tipo, problema, id_cliente) VALUES (?, ?, ?)";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setString(1, electrodomestico.getTipo());
			ps.setString(2, electrodomestico.getProblema());
			ps.setInt(3, electrodomestico.getCliente_id().getId());
			ps.executeUpdate();
			}
		}
	
	@Override
	public void editElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {

		String sql = "UPDATE electrodomestico SET tipo = ?, problema = ? WHERE id_electrodomestico = ?";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setString(1, electrodomestico.getTipo());
			ps.setString(2, electrodomestico.getProblema());
			ps.setInt(3, electrodomestico.getId());
			ps.executeUpdate();
			}
		}
	
	@Override
	public void deleteElectrodomestico(int electrodomesticoId) throws SQLException, NamingException {
		
	}
	
	@Override
	public Electrodomestico findLastCreatedElectrodomestico() throws SQLException, NamingException {
		
		String sql = "SELECT * FROM electrodomestico ORDER BY id_electrodomestico DESC LIMIT 1";
		try (
				Connection conex = DBUtils.getConexion();
				Statement st 	 = conex.createStatement();
			) {
			
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				int id 			= rs.getInt("id_electrodomestico");
				String tipo 	= rs.getString("tipo");
				String problema = rs.getString("problema");
				int idCliente 	= rs.getInt("id_cliente");
				Cliente cliente = clientesDAO.findClienteById(idCliente);
				
				return new Electrodomestico(id, tipo, problema, cliente);
			}
		}
		return null;
	}
	
	@Override
	public Electrodomestico findElectrodomesticoByClienteId(int clienteId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM electrodomestico WHERE id_cliente = ?";
		try (
				Connection conex     = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1,  clienteId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 			= rs.getInt("id_electrodomestico");
				String tipo 	= rs.getString("tipo");
				String problema = rs.getString("problema");
				int idCliente 	= rs.getInt("id_cliente");
				Cliente cliente = clientesDAO.findClienteById(idCliente);
				
				return new Electrodomestico(id, tipo, problema, cliente);
			}
		}
		return null;
	}
		
}
