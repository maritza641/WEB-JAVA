package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import dao.AlumnoDAO;
import dao.AlumnoDAOImp;
import dao.CalificacionDAO;
import dao.CalificacionDAOImp;
import dao.ModuloDAO;
import dao.ModuloDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Calificacion;
import modelo.Modulo;
import modelo.Promedio;

public class JavaPeopleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AlumnoDAO alumnoDAO;
	private ModuloDAO moduloDAO;
	private CalificacionDAO calificacionDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.alumnoDAO 		 = new AlumnoDAOImp();
		this.moduloDAO 		 = new ModuloDAOImp();
		this.calificacionDAO = new CalificacionDAOImp(alumnoDAO, moduloDAO);
	}

	public JavaPeopleController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "listar":
			List<Alumno> alumnos = null;
			try {
				alumnos= alumnoDAO.findAllAlumnos();
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("alumnos", alumnos);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/listado-estudiantes.jsp").forward(request, response);
			break;
			
		case "formulario":
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-estudiantes.jsp").forward(request, response);
			break;
			
		case "fNota":
			int idAlumno = Integer.parseInt(request.getParameter("id"));
			
			Alumno alumno = null;
			List<Modulo> modulos = null;
			try {
				alumno = alumnoDAO.findAlumnoById(idAlumno);
				modulos = moduloDAO.findAllModulos();
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("modulos", modulos);
			request.setAttribute("alumno", alumno);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-calificaciones.jsp").forward(request, response);
			break;
			
		case "informar":
			idAlumno = Integer.parseInt(request.getParameter("id"));
			List<Calificacion> calificaciones = null;
			List<Promedio> promedios 		  = null;
			try {
				calificaciones = calificacionDAO.findAllCalificacionesById(idAlumno);
				promedios = calificacionDAO.findAverageCalificacionById(idAlumno);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("promedios", promedios);
			request.setAttribute("calificaciones", calificaciones);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/listado-calificaciones.jsp").forward(request, response);
			break;
		
		case "prepareEditNota":
			int idNota = Integer.parseInt(request.getParameter("idNota"));
			Calificacion calificacion = null;
			try {
				calificacion = calificacionDAO.findCalificacionById(idNota);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("calificacion", calificacion);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-editar-calificaciones.jsp").forward(request, response);
			break;
	
		default:
			response.sendError(500);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch (accion) {
		case "addEstudiante":
			String nombres 	 = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String rut 		 = request.getParameter("rut");
			String genero 	 = request.getParameter("genero");
			String fono 	 = request.getParameter("fono");
			String asignatura 	 = request.getParameter("asignatura");
			if(genero.equals("none")) {
				request.setAttribute("codigo", 0);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/form-estudiantes.jsp").forward(request, response);
				break;
			}
			
			if(asignatura.equals("none")) {
				request.setAttribute("codigo", 1);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/form-estudiantes.jsp").forward(request, response);
				break;
			}
			
			Alumno alumno = new Alumno(nombres, apellidos, rut, genero, fono, asignatura);
			try {
				alumnoDAO.createAlumno(alumno);
				request.setAttribute("codigo", 1);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				request.setAttribute("codigo", 0);
				request.getRequestDispatcher("index.jsp").forward(request, response);
					e.printStackTrace();
			}
			break;
			
		case "addNota":
			int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
			int idModulo = Integer.parseInt(request.getParameter("modulo"));
			float nota   = Float.parseFloat(request.getParameter("nota"));
			Calificacion calificacion = null;
			try {
				calificacion = calificacionDAO.findCalificacionByForeignIds(idModulo, idAlumno);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			if(calificacion == null) {
				calificacion = new Calificacion(nota, 0, idAlumno, idModulo);
			}
			
			calificacion.setNota(nota);
			calificacion.setNumeroEvaluacion(calificacion.getNumeroEvaluacion()+1);
			try {
				calificacionDAO.createCalificacion(calificacion);
				request.setAttribute("codigo", 2);
				request.setAttribute("nota", nota);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
			
		case "editNota":
			int idNota = Integer.parseInt(request.getParameter("idNota"));
			nota = Float.parseFloat(request.getParameter("nota"));
			try {
				calificacion = calificacionDAO.findCalificacionById(idNota);
				calificacion.setNota(nota);
				calificacionDAO.editCalificacion(calificacion);
				request.setAttribute("codigo", 3);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
		default:
			response.sendError(500);
			break;
		}
	}
	
}
	
	
	
