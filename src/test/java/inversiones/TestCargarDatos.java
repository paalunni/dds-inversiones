package inversiones;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

public class TestCargarDatos {

	Empresa empresa = new Empresa();

	@Test
	public void insertarDatosValidarEmpresasNuevas() throws IOException, ParseException{
		empresa.cargarDatos("C:\\archivo.txt");
		Assert.assertEquals(3, empresa.empresas.size());
	}
	
}
