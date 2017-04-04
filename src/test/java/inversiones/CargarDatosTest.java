package inversiones;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class CargarDatosTest {

	@Test
	public void insertarDatosValidarEmpresasNuevas() throws IOException, ParseException{
		Empresa.importarCuentasDesdeArchivo("C:\\archivo.txt");
		Assert.assertEquals(3, Empresa.getEmpresas().size());
	}
}