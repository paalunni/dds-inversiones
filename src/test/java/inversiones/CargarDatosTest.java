package inversiones;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class CargarDatosTest {

	//TODO Buscar una forma mas copada de hacer que los tests sean independientes.
	@After
	public void tearDown() {
		Empresa.clean();
	}
	
	/*
	 * TODO Los archivos deberian usar path relativos, no absolutos. No podemos forzar
	 * a todos a tener los archivos en el mismo lugar, ni a usar el mismo OS
	 */
	@Test
	public void insertar2EmpresasNuevas() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("C:\\archivo_2_empresas.txt");
		Assert.assertEquals(2, Empresa.getEmpresas().size());
	}
	
	@Test
	public void insertarRegistrosMismaEmpresa() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("C:\\archivo_misma_empresa.txt");
		Assert.assertEquals(1, Empresa.getEmpresas().size());
	}
	
	@Test
	public void validarCreacionPeriodosMismaEmpresa() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("C:\\archivo_misma_empresa.txt");
		Assert.assertEquals(3, Empresa.getAllPeriodos().size());
	}
	
	@Test
	public void eliminarVariasEmpresasYEvaluarSize() throws IOException, ParseException{
		ArrayList<String> nombres = new ArrayList<String>(3);
		nombres.add("Apple");
		nombres.add("Microsoft");
		nombres.add("Samsung");
		
		for(String unNombre : nombres) {
			new Empresa(unNombre);
		}
		
		nombres.remove("Apple");
		Empresa.eliminarEmpresasConNombre(nombres);
		
		Assert.assertEquals(1, Empresa.getEmpresas().size());
	}
	
	@Test
	public void eliminarVariasEmpresasYEvaluarRestante() throws IOException, ParseException{
		ArrayList<String> nombres = new ArrayList<String>(3);
		nombres.add("Apple");
		nombres.add("Microsoft");
		nombres.add("Samsung");
		
		for(String unNombre : nombres) {
			new Empresa(unNombre);
		}
		
		nombres.remove("Apple");
		Empresa.eliminarEmpresasConNombre(nombres);
		
		Assert.assertEquals("Apple", Empresa.getEmpresas().get(0).getNombre());
	}
	
	@Test
	public void obtenerVariasEmpresas() throws IOException, ParseException{
		ArrayList<String> nombres = new ArrayList<String>(3);
		nombres.add("Apple");
		nombres.add("Microsoft");
		nombres.add("Samsung");
		
		for(String unNombre : nombres) {
			new Empresa(unNombre);
		}
		
		Assert.assertEquals(3, Empresa.obtenerEmpresasConNombre(nombres).size());
	}
}