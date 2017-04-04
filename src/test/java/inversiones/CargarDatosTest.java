package inversiones;

import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class CargarDatosTest {

	@After
	public void tearDown() {
		Empresa.cleanAll();
	}
	
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
		Assert.assertEquals(3, Empresa.getPeriodos().size());
	}
}