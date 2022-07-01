package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;
import modelo.Calificacion;
import modelo.Modulo;
import modelo.Promedio;

public class CalificacionDAOImp implements CalificacionDAO {
	
	private AlumnoDAO alumnoDAO;
	private ModuloDAO moduloDAO;
	
	public CalificacionDAOImp(AlumnoDAO alumnoDAO, ModuloDAO moduloDAO) {
		this.alumnoDAO = alumnoDAO;
		this.moduloDAO = moduloDAO;
	}

	public List<Calificacion> findAllCalificacionesById(int alumnoId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM calificacion WHERE id_alumno = ?";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			List<Calificacion> calificaciones = new ArrayList<>();
			while(rs.next()) {
				int id 			 	 = rs.getInt("id_calificacion");
				float nota	 	 	 = rs.getFloat("nota");
				int numeroEvaluacion = rs.getInt("numeroEvaluacion");
				int id_alumno 		 = rs.getInt("id_alumno");
				int id_modulo	 	 = rs.getInt("id_modulo");
				Alumno alumno	 	 = alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo	 	 = moduloDAO.findModuloById(id_modulo);
					
				Calificacion calificacion = new Calificacion(id, nota, numeroEvaluacion, alumno, modulo);
					calificaciones.add(calificacion);
			}
		return calificaciones;
		}
	}

	public Calificacion findCalificacionByForeignIds(int moduloId, int alumnoId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM calificacion WHERE id_modulo = ? AND id_alumno = ? ORDER BY numeroevaluacion DESC LIMIT 1";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, moduloId);
			ps.setInt(2, alumnoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				float nota	 	 	 = rs.getFloat("nota");
				int numeroEvaluacion = rs.getInt("numeroEvaluacion");
				int idAlumno 		 = rs.getInt("id_alumno");
				int idModulo	 	 = rs.getInt("id_modulo");
					
				return new Calificacion(nota, numeroEvaluacion, idAlumno, idModulo);	
			}		
		}
		return null;
	}

	public void createCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		
		String sql = "INSERT INTO calificacion (numeroevaluacion, nota, id_alumno, id_modulo) VALUES (?, ?, ?, ?)";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, calificacion.getNumeroEvaluacion());
			ps.setFloat(2, calificacion.getNota());
			ps.setInt(3, calificacion.getId_alumno());
			ps.setInt(4, calificacion.getId_modulo());
			ps.executeUpdate();	
		}	
		
	}
		
	public void editCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		
		String sql = "UPDATE calificacion SET nota = ? WHERE id_alumno, id_calificacion = ?";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setFloat(1, calificacion.getNota());
			ps.setInt(2, calificacion.getId_calificacion());
			ps.executeUpdate();	
		}		
		
	}
	
	public Calificacion findCalificacionById(int calificacionId) throws SQLException, NamingException {
		
		String sql = "SELECT * FROM calificacion WHERE id_calificacion = ?";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, calificacionId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 			 	= rs.getInt("id_calificacion");
				float nota	 	 	= rs.getFloat("nota");
				int numeroEvaluacion = rs.getInt("numeroEvaluacion");
				int id_alumno    	= rs.getInt("id_alumno");
				int id_modulo 	 	= rs.getInt("id_modulo");
				Alumno alumno		= alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo 	    = moduloDAO.findModuloById(id_modulo);
					
				return new Calificacion(id, nota, numeroEvaluacion, alumno, modulo);
			}
		}
		return null;
			
	}		
		

	public void deleteCalificacion(int calificacionId) throws SQLException, NamingException {
		
	}

	public Calificacion findLastCreatedCalificacion() throws SQLException, NamingException {
	
		return null;
	}

	public List<Promedio> findAverageCalificacionById(int alumnoId) throws SQLException, NamingException {
		
		String sql = "SELECT id_modulo, id_alumno, AVG(nota) FROM calificacion WHERE id_alumno = ? GROUP BY id_modulo, id_alumno";
		try (
			Connection conex 	 = DBUtils.getConexion();
			PreparedStatement ps = conex.prepareStatement(sql);
			) {
			
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			List<Promedio> promedios = new ArrayList<>();
			while(rs.next()) {
				float average	 	= rs.getFloat("avg");
				int id_modulo 	 	= rs.getInt("id_modulo");
				int id_alumno    	= rs.getInt("id_alumno");
				Alumno alumno		= alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo 	    = moduloDAO.findModuloById(id_modulo);
					
				Promedio promedio = new Promedio(average, modulo, alumno);
					promedios.add(promedio);
				}
				return promedios;
			}
		}

	}
