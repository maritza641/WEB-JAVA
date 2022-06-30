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

public class ClienteDAOImp implements ClienteDAO {
	
	@Override
	public List<Cliente> findAllCliente() throws SQLException, NamingException {
		try(
				Connection conex = DBUtils.getConexion();
				Statement st    = conex.createStatement();
			) {
			
			String sql = "SELECT * FROM cliente";
			ResultSet rs = st.executeQuery(sql);
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			while(rs.next()) {
				int id 			 = rs.getInt("id_cliente");
				String nombre 	 = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String telefono  = rs.getString("telefono");
				Cliente cliente  = new Cliente(id, nombre, direccion, telefono);
				clientes.add(cliente);
			}
			return clientes;
		}
	}
	
	@Override
	public Cliente findClienteById(int clienteId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, clienteId);
			ResultSet rs =ps.executeQuery();
			if(rs.next()) {
				int id 			 = rs.getInt("id_cliente");
				String nombre 	 = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String telefono  = rs.getString("telefono");
				
				return new Cliente(id, nombre, direccion, telefono);
			}
		}
		return null;
	}
	
	@Override
	public void createCliente(Cliente cliente) throws SQLException, NamingException {
		
		String sql = "INSERT INTO cliente(nombre, direccion, telefono) VALUES (?, ?, ?)";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);	
			) {
				ps.setString(1, cliente.getNombre());
				ps.setString(2, cliente.getDireccion());
				ps.setString(3, cliente.getTelefono());
				ps.executeUpdate();
		}		
	}
	
	@Override
	public void editCliente(Cliente cliente) throws SQLException, NamingException {
		
		String sql = "UPDATE cliente SET nombre = ?, direccion =?, telefono =? WHERE id_cliente = ?";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
				ps.setString(1, cliente.getNombre());
				ps.setString(2, cliente.getDireccion());
				ps.setString(3, cliente.getTelefono());
				ps.setInt(4, cliente.getId());
				ps.executeUpdate();
		}
	}
	
	@Override
	public void deleteCliente(int clienteId) throws SQLException, NamingException {
		
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		try (
				Connection conex 	 = DBUtils.getConexion();
				PreparedStatement ps = conex.prepareStatement(sql);
			) {
				ps.setInt(1, clienteId);
				ps.executeUpdate();
		}
	}
	
	@Override
	public Cliente findLastCreatedCliente() throws SQLException, NamingException {
		
		try(
				Connection conex = DBUtils.getConexion();
				Statement st     = conex.createStatement();
			) {
			
			String sql = "SELECT * FROM cliente ORDER BY id_cliente DESC LIMIT 1";
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				int id 			 = rs.getInt("id_cliente");
				String nombre 	 = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String telefono  = rs.getString("telefono");
				
				return new Cliente(id, nombre, direccion, telefono);
			}			
		}
		return null;
	}

}
