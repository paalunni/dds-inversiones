package inversiones;

public class Cuenta {

	private String nombre;
	private int valor;
	
	public Cuenta (String nombre, int valor) {
		this.setNombre(nombre);
		this.setValor(valor);
	}
	
	public String orElseNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int orElseValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}