package inversiones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empresa {

	private String nombre;
	private List<Periodo> periodos = new ArrayList<Periodo>();
	private static List<Empresa> empresas = new ArrayList<Empresa>();
	
	public Empresa(String nombre) {
		this.nombre = nombre;
		empresas.add(this);
	}
	
	public static List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public static Empresa obtenerEmpresaConNombre(String nombre) {
		return empresas.stream().filter(unaEmpresa -> nombre.equals(unaEmpresa.nombre)).findFirst().get();
	}
	
	public static void eliminarEmpresaConNombre(String nombre) {
		//Implementar algo aqui
	}
	
	public static void eliminarEmpresa(Empresa empresa) {
		empresas.remove(empresa);
	}
	
	public static void importarCuentasDesdeArchivo (String pathArchivo) throws IOException, ParseException {
		//Levantar archivo y cargar cuentas		
		FileReader fr = new FileReader (new File (pathArchivo));
		BufferedReader br = new BufferedReader(fr);
		String linea;
		String[] arraySubstrings;
		Empresa empresa;
		
		//El formato de las lineas es nombreEmpresa;fechaInicio;fechaFin;cuenta;valor
		while ((linea = br.readLine()) != null) {
			arraySubstrings = linea.split(";");
			empresa = Empresa.obtenerEmpresaConNombre(arraySubstrings[0]);
			if(empresa == null) {
				empresa = new Empresa(arraySubstrings[0]);
			}
			empresa.importarCuenta(arraySubstrings[1], arraySubstrings[2], arraySubstrings[3], arraySubstrings[4]);
		}
		fr.close();
	}
	
	public void importarCuenta(String fechaInicioString, String fechaFinString, String cuentaString, String valorCuentaString) throws ParseException {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio;
		Date fechaFin;
		
		fechaInicio = formatoDeFecha.parse(fechaInicioString);
		fechaFin = formatoDeFecha.parse(fechaFinString);
		
		Periodo periodo = obtenerPeriodo(fechaInicio, fechaFin);
		
		if(periodo == null) {
			periodo = new Periodo(fechaInicio, fechaFin);
			agregarPeriodo(periodo);
		}
		
		periodo.importarCuenta(cuentaString, valorCuentaString);
	}
	
	public Periodo obtenerPeriodo(Date fechaInicio, Date fechaFin) {
		return periodos.stream().filter(unPeriodo -> unPeriodo.getFechaInicio() == fechaInicio && unPeriodo.getFechaFin() == fechaFin).findFirst().get();
	}
	
	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}
}
