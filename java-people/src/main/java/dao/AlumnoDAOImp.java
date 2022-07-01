package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {

	@Override
	public List<Alumno> findAllAlumnos() throws SQLException, NamingException {
		try (
			Connection conex = DBUtils.getConexion();
			Statement st     = conex.createStatement();
		) {
		
			String sql   = "SELECT * FROM estudiante";
			ResultSet rs = st.executeQuery(sql);
			List<Alumno> alumnos = new ArrayList<>();
			while(rs.next()) {
				int id 			 	= rs.getInt("id_alumno");
				String nombres 	 	= rs.getString("nombres");
				String apellidos 	= rs.getString("apellidos");
				String rut 		 	= rs.getString("rut");
				String genero 	 	= rs.getString("genero");
				String fono 	 	= rs.getString("fono");
				String asignatura   = rs.getString("asignatura");
				
				Alumno alumno = new Alumno(id, nombres, apellidos, rut, genero, fono, asignatura);
				alumnos.add(alumno);
			}
			return alumnos;
		}
		
	}

	@Override
	public Alumno findAlumnoById(int alumnoId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM estudiante WHERE id_alumno = ?";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 			 	= rs.getInt("id_alumno");
				String nombres 	 	= rs.getString("nombres");
				String apellidos 	= rs.getString("apellidos");
				String rut 		 	= rs.getString("rut");
				String genero 	 	= rs.getString("genero");
				String fono 	 	= rs.getString("fono");
				String asignatura   = rs.getString("asignatura");
					
				return new Alumno(id, nombres, apellidos, rut, genero, fono, asignatura);
			}
		}
		return null;
			
	}	
		
	@Override
	public void createAlumno(Alumno alumno) throws SQLException, NamingException {
		
		String sql = "INSERT INTO estudiante (rut, nombres, apellidos, genero, fono, asignatura) VALUES (?, ?, ?, ?, ?, ?)";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setString(1, alumno.getRut());
			ps.setString(2, alumno.getNombres());
			ps.setString(3, alumno.getApellidos());
			ps.setString(4, alumno.getGenero());
			ps.setString(5, alumno.getFono());
			ps.setString(6, alumno.getAsignatura());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void editAlumno(Alumno alumno) throws SQLException, NamingException {
		
	}

	@Override
	public void deleteAlumno(int alumnoId) throws SQLException, NamingException {
		
	}

	@Override
	public Alumno findLastCreatedAlumno() throws SQLException, NamingException {
		
		return null;
	}

}
