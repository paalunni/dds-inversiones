package inversiones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Periodo {

	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	private Date fechaInicio;
	private Date fechaFin;
	
	public Periodo(Date fechaComienzo, Date fechaFin) {
		this.fechaInicio = fechaComienzo;
		this.fechaFin = fechaFin;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void importarCuenta(String nombreCuenta, String valorCuenta) {
		Cuenta cuenta = obtenerCuenta(nombreCuenta);
		
		if(cuenta == null) {
			cuenta = new Cuenta(nombreCuenta, Integer.parseInt(valorCuenta));
		} else {
			cuenta.setValor(Integer.parseInt(valorCuenta));
		}
	}
	
	public Cuenta obtenerCuenta(String nombreCuenta) {
		return cuentas.stream().filter(unaCuenta -> unaCuenta.getNombre().equals(nombreCuenta)).findFirst().get();
	}
	
}
