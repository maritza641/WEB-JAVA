package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.NamingException;

import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.ElectrodomesticosDAO;
import dao.ElectrodomesticosDAOImp;
import dao.OrtrabDAO;
import dao.OrtrabDAOImp;
import modelo.*;


public class OrdenController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDAO;
	private ElectrodomesticosDAO electrodomesticosDAO;
	private OrtrabDAO ortrabDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.clienteDAO = new ClienteDAOImp();
		this.electrodomesticosDAO = new ElectrodomesticosDAOImp(this.clienteDAO);
		this.ortrabDAO = new OrtrabDAOImp(this.electrodomesticosDAO);
	}
       
    public OrdenController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String indicador = request.getParameter("indicador");
		
		switch (indicador) {
		
		case "registrarcliente":
				List<Cliente> cliente = null;
				try {
					cliente = clienteDAO.findAllCliente();
					} catch (Exception e) {
						e.printStackTrace();
						response.sendError(500);
						return;
				}
				request.setAttribute("cliente", cliente);
				
				request.getRequestDispatcher("/WEB-INF/jsp/vista/registro-cliente.jsp").forward(request, response);
				break;
				
		case "registrarelec":
				List<Electrodomestico> electrodomesticos = null;
				try {
					electrodomesticos = electrodomesticosDAO.findAllElectrodomesticos();
					} catch (Exception e) {
						e.printStackTrace();
						response.sendError(500);
						return;
				}
				request.setAttribute("electrodomesticos", electrodomesticos);
				
				request.getRequestDispatcher("/WEB-INF/jsp/vista/registro-electro.jsp").forward(request, response);
				break;
				
		case "registrarot":
				List<OrdenTrab> ot = null;
				try {
					ot = ortrabDAO.findAllOrdenDeTrabajo();		
					} catch (Exception e) {
						e.printStackTrace();
						response.sendError(500);
						return;
				}
				request.setAttribute("ot", ot);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/registro-ortrab.jsp").forward(request, response);
				break;
				
		case "solicitud":
				request.getRequestDispatcher("/WEB-INF(jsp/vista/solicitud1.jsp").forward(request, response);
				break;
				
		case "editarot":
				int id = Integer.parseInt(request.getParameter("id"));
				try {
					OrdenTrab otEdit = ortrabDAO.findOrdenDeTrabajoById(id);
					request.setAttribute("ot", otEdit);
					request.getRequestDispatcher("/WEB-INF/jsp/vista/modificar-orden.jsp").forward(request, response);
					} catch (SQLException | NamingException e) {
						e.printStackTrace();
						response.sendError(500);
						return;
				}
				break;
				
		case "verot":
				id = Integer.parseInt(request.getParameter("id"));
				try {
					OrdenTrab otEdit = ortrabDAO.findOrdenDeTrabajoById(id);
					request.setAttribute("ot", otEdit);
					request.getRequestDispatcher("/WEB-INF/jsp/vista/orden-trab.jsp").forward(request, response);
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				break;
			default:
					response.sendError(500);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String indicador = request.getParameter("indicador");
		
		switch (indicador) {
			
		case "agregarCliente":
			String nombre = request.getParameter("nombre");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			Cliente nuevoCliente = new Cliente(nombre, direccion, telefono);
				try {
					clienteDAO.createCliente(nuevoCliente);
					nuevoCliente = clienteDAO.findLastCreatedCliente();
					
					request.setAttribute("cliente", nuevoCliente);
					request.getRequestDispatcher("/WEB-INF/jsp/vista/solicitud2.jsp").forward(request, response);
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
				}
				break;
				
		case "agregarElectrodomestico":
			int idCliente = Integer.parseInt(request.getParameter("id"));
			Cliente cliente = null;
				try {
					cliente = clienteDAO.findClienteById(idCliente);
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
				}
				String tipoElectro = request.getParameter("tipoElectro");
				String problemaElectro = request.getParameter("problemaElectro");
				
				Electrodomestico electrodomestico = new Electrodomestico(tipoElectro, problemaElectro, cliente);
				try {
					electrodomesticosDAO.createElectrodomestico(electrodomestico);
					electrodomestico = electrodomesticosDAO.findLastCreatedElectrodomestico();
					LocalDate fecha = LocalDate.now();
					
					String estado = "Pendiente";
					OrdenTrab ot =new OrdenTrab(estado, fecha, fecha, electrodomestico);
					ortrabDAO.createOrdenDeTrabajo(ot);
					
					request.setAttribute("cliente", cliente);
					request.getRequestDispatcher("WEB-INF/jsp/vista/solicitud2.jsp").forward(request, response);
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
				}
				break;
				
		case "finalizar":
			idCliente = Integer.parseInt(request.getParameter("id"));
			try {
				Electrodomestico elec = electrodomesticosDAO.findElectrodomesticoByClienteId(idCliente);
				if(elec == null) {
					Cliente clienteAEliminar = clienteDAO.findLastCreatedCliente();
					clienteDAO.deleteCliente(clienteAEliminar.getId());
					request.setAttribute("success", 0);
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				}
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			request.setAttribute("success", 1);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		case "finalizarEditar":
			int idot 		   	    = Integer.parseInt(request.getParameter("idot"));
			idCliente 		        = Integer.parseInt(request.getParameter("idCliente"));
			int idElectro	        = Integer.parseInt(request.getParameter("idElectrodomestico"));
			String estadoOrden      = request.getParameter("estadoOrden");
			tipoElectro 	        = request.getParameter("tipoElectro");
			problemaElectro         = request.getParameter("problemaElectro");
			String nombreCliente    = request.getParameter("nombreCliente");
			String direccionCliente = request.getParameter("direccionCliente");
			String telefonoCliente  = request.getParameter("telefonoCliente");
			try {
				electrodomestico = electrodomesticosDAO.findElectrodomesticoById(idElectro);
				cliente 		 = electrodomestico.getCliente_id();
				
				electrodomestico.setTipo(tipoElectro);
				electrodomestico.setProblema(problemaElectro);
				
				cliente.setNombre(nombreCliente);
				cliente.setDireccion(direccionCliente);
				cliente.setTelefono(telefonoCliente);
				
				electrodomesticosDAO.editElectrodomestico(electrodomestico);
				clienteDAO.editCliente(cliente);
				
				ortrabDAO.editOredenTrabajo(new OrdenTrab(idot, estadoOrden));
				request.setAttribute("success", 2);
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			default:
				response.sendError(500);
			}
		}
}
