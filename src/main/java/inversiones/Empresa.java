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

	public static void clean() {
		empresas = new ArrayList<Empresa>();
	}

	public static List<Periodo> getAllPeriodos() {
		List<Periodo> periodos = new ArrayList<Periodo>();
		getEmpresas().stream().forEach(empresa -> {
			periodos.addAll(empresa.periodos);
		});

		return periodos;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}
	
	public static Empresa obtenerEmpresaConNombre(String nombre) {
		return empresas.stream().filter(unaEmpresa -> nombre.equals(unaEmpresa.nombre)).findFirst().orElse(null);
	}
	
	public static void eliminarEmpresaConNombre(String nombre) {
		//Implementar algo aqui
	}
	
	public static void eliminarEmpresa(Empresa empresa) {
		empresas.remove(empresa);
	}
	
	public static void importarCuentasDesdeArchivo (String pathArchivo) {
		
		/*
		 * Levantar archivo y cargar cuentas
		 * Formato: nombreEmpresa;fechaInicio;fechaFin;cuenta;valor
		 */

		FileReader fr = null;
		BufferedReader br;
		String linea;
		String[] arraySubstrings;
		Empresa empresa;

		try {
			fr = new FileReader (new File (pathArchivo));
			br = new BufferedReader(fr);
			
			while ((linea = br.readLine()) != null) {
				arraySubstrings = linea.split(";");
				empresa = Empresa.obtenerEmpresaConNombre(arraySubstrings[0]);
				if(empresa == null) {
					empresa = new Empresa(arraySubstrings[0]);
				}
				empresa.importarCuenta(arraySubstrings[1], arraySubstrings[2], arraySubstrings[3], arraySubstrings[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}		
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
		return periodos.stream().filter(unPeriodo -> unPeriodo.getFechaInicio() == fechaInicio && unPeriodo.getFechaFin() == fechaFin).findFirst().orElse(null);
	}
	
	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}
}
