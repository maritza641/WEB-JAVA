package modelo;

import java.time.LocalDate;

public class OrdenTrab {

	private int id;
	private String estado;
	private LocalDate fechaSolicitud;
	private LocalDate fechaActOrden;
	private Electrodomestico electrodomestico_id;
	
	protected OrdenTrab() {
		super();
	}

	public OrdenTrab(String estado, LocalDate fechaSolicitud, LocalDate fechaActOrden,
			Electrodomestico electrodomestico_id) {
		this.estado = estado;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaActOrden = fechaActOrden;
		this.electrodomestico_id = electrodomestico_id;
	}

	public OrdenTrab(int id, String estado, LocalDate fechaSolicitud, LocalDate fechaActOrden,
			Electrodomestico electrodomestico_id) {
		this.id = id;
		this.estado = estado;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaActOrden = fechaActOrden;
		this.electrodomestico_id = electrodomestico_id;
	}

	public OrdenTrab(int id, String estado) {
		this.id = id;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaActOrden() {
		return fechaActOrden;
	}

	public void setFechaActOrden(LocalDate fechaActOrden) {
		this.fechaActOrden = fechaActOrden;
	}

	public Electrodomestico getElectrodomestico_id() {
		return electrodomestico_id;
	}

	public void setElectrodomestico_id(Electrodomestico electrodomestico_id) {
		this.electrodomestico_id = electrodomestico_id;
	}

}
