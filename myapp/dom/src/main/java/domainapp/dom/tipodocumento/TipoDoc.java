package domainapp.dom.tipodocumento;

public enum TipoDoc {

	DNI("DNI: Documento Nacional de Identidad"), 
	LC("LC: Libreta Civica"), 
	LE("LE:  Libreta de Enrolamiento"), 
	CI("CI: Cedula de Identidad"), 
	PASAPORTE("Pasaporte");
	
	private final String nombre;

	public String getNombre() {return nombre;}
	private TipoDoc(String nom) {nombre = nom;}

	@Override
	public String toString() {
		return this.nombre;
	}
}
