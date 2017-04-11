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
import java.util.stream.Collectors;

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
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Periodo> getPeriodos() {
		return periodos;
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
	
	public static Empresa obtenerEmpresaConNombre(String nombre) {
		return empresas.stream().filter(unaEmpresa -> nombre.equals(unaEmpresa.nombre)).findFirst().orElse(null);
	}
	
	public static List<Empresa> obtenerEmpresasConNombre(List<String> nombres) {
		return empresas.stream().filter(unaEmpresa -> nombres.contains(unaEmpresa.nombre)).collect(Collectors.toList());
	}
	
	public static void eliminarEmpresaConNombre(String nombre) {
		Empresa empresaAEliminar = obtenerEmpresaConNombre(nombre);
		if(empresaAEliminar != null) {
			Empresa.eliminarEmpresa(empresaAEliminar);
		}
	}
	
	public static void eliminarEmpresasConNombre(List<String> nombres) {
		Empresa.eliminarEmpresas(obtenerEmpresasConNombre(nombres));
	}
	
	public static void eliminarEmpresa(Empresa empresa) {
		empresas.remove(empresa);
	}
	
	public static void eliminarEmpresas(List<Empresa> unasEmpresas) {
		empresas.removeAll(unasEmpresas);
	}
	
	public void agregarPeriodo(Periodo periodo) {
		periodos.add(periodo);
	}
	
	public Periodo obtenerPeriodo(Date fechaInicio, Date fechaFin) {
		return periodos.stream().filter(unPeriodo -> unPeriodo.getFechaInicio().equals(fechaInicio) && unPeriodo.getFechaFin().equals(fechaFin)).findFirst().orElse(null);
	}
	
	public void eliminarPeriodo(Date fechaInicio, Date fechaFin) {
		eliminarPeriodo(obtenerPeriodo(fechaInicio, fechaFin));
	}
	
	public void eliminarPeriodo(Periodo periodo) {
		periodos.remove(periodo);
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
	
	public float consultarCuenta(String nombreCuenta, Date fechaDesde, Date fechaHasta) throws PeriodoNoExisteException, CuentaNoExisteException {
		Periodo periodo = obtenerPeriodo(fechaDesde, fechaHasta);
		
		if(periodo == null) {
			throw new PeriodoNoExisteException();
		}
		
		return periodo.consultarCuenta(nombreCuenta);
	}
}
