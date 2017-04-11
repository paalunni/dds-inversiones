package inversiones;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Empresa.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
		Assert.assertEquals(2, Empresa.getEmpresas().size());
	}
	
	@Test
	public void insertarRegistrosMismaEmpresa() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");
		Assert.assertEquals(1, Empresa.getEmpresas().size());
	}
	
	@Test
	public void validarCreacionPeriodosMismaEmpresa() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");
		Assert.assertEquals(3, Empresa.getAllPeriodos().size());
	}
	
	@Test
	public void eliminarVariasEmpresasYEvaluarSize() {
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
	public void eliminarVariasEmpresasYEvaluarRestante() {
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
	public void obtenerVariasEmpresas() {
		ArrayList<String> nombres = new ArrayList<String>(3);
		nombres.add("Apple");
		nombres.add("Microsoft");
		nombres.add("Samsung");
		
		for(String unNombre : nombres) {
			new Empresa(unNombre);
		}
		
		Assert.assertEquals(3, Empresa.obtenerEmpresasConNombre(nombres).size());
	}

	
	@Test
	public void consultarCuenta() throws PeriodoNoExisteException, CuentaNoExisteException, ParseException {
		Empresa.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Empresa empresa = Empresa.obtenerEmpresaConNombre("Apple");

		float valor = empresa.consultarCuenta("mac", dateFormat.parse("01/01/2015"), dateFormat.parse("01/01/2016"));
		
		Assert.assertEquals(1000.0f, valor, 0.0f);
	}
	
	@Test(expected = PeriodoNoExisteException.class)
	public void consultarCuentaConPeriodoInexistente() throws PeriodoNoExisteException, CuentaNoExisteException, ParseException {
		Empresa.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Empresa empresa = Empresa.obtenerEmpresaConNombre("Apple");

		float valor = empresa.consultarCuenta("mac", dateFormat.parse("02/01/2015"), dateFormat.parse("01/12/2016"));
		
		Assert.assertEquals(1000.0f, valor, 0.0f);
	}
	
	@Test(expected = CuentaNoExisteException.class)
	public void consultarCuentaConCuentaInexistente() throws PeriodoNoExisteException, CuentaNoExisteException, ParseException {
		Empresa.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Empresa empresa = Empresa.obtenerEmpresaConNombre("Apple");

		float valor = empresa.consultarCuenta("estaCuentaNoExiste", dateFormat.parse("01/01/2015"), dateFormat.parse("01/01/2016"));
		
		Assert.assertEquals(1000.0f, valor, 0.0f);
	}
}