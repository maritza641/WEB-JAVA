package modelo;

public class Alumno {

		private int id;
		private String nombres;
		private String apellidos;
		private String rut;
		private String genero;
		private String fono;
		private String asignatura;
		
		protected Alumno() {
			super();
		}

		public Alumno(int id, String nombres, String apellidos, String rut, String genero, String fono,
				String asignatura) {
			this.id = id;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.rut = rut;
			this.genero = genero;
			this.fono = fono;
			this.asignatura = asignatura;
		}

		public Alumno(String nombres, String apellidos, String rut, String genero, String fono, String asignatura) {
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.rut = rut;
			this.genero = genero;
			this.fono = fono;
			this.asignatura = asignatura;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombres() {
			return nombres;
		}

		public void setNombres(String nombres) {
			this.nombres = nombres;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getRut() {
			return rut;
		}

		public void setRut(String rut) {
			this.rut = rut;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public String getFono() {
			return fono;
		}

		public void setFono(String fono) {
			this.fono = fono;
		}

		public String getAsignatura() {
			return asignatura;
		}

		public void setCurso(String asignatura) {
			this.asignatura = asignatura;
		}

}
