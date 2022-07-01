package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Modulo;

public class ModuloDAOImp implements ModuloDAO {

	@Override
	public List<Modulo> findAllModulos() throws SQLException, NamingException {
		try (
				Connection conex = DBUtils.getConexion();
				Statement st     = conex.createStatement();
			) {
			
				String sql   = "SELECT * FROM modulo";
				ResultSet rs = st.executeQuery(sql);
				List<Modulo> modulos = new ArrayList<>();
				while(rs.next()) {
					int id 			 = rs.getInt("id_modulo");
					String nombre 	 = rs.getString("nombre");
					
					Modulo modulo = new Modulo(id, nombre);
					modulos.add(modulo);
			}
			return modulos;
		}
			
	}
		
	@Override
	public Modulo findModuloById(int moduloId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM modulo WHERE id_alumno = ?";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, moduloId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 			 = rs.getInt("id_modulo");
				String nombre 	 = rs.getString("nombre");
					
				return new Modulo(id, nombre);
			}
		}		
		return null;
	}

	@Override
	public void createModulo(Modulo modulo) throws SQLException, NamingException {
		
	}

	@Override
	public void editModulo(Modulo modulo) throws SQLException, NamingException {
		
	}

	@Override
	public void deleteModulo(int moduloId) throws SQLException, NamingException {
		
	}

	@Override
	public Modulo findLastCreatedModulo() throws SQLException, NamingException {
	
		return null;
	}

}
