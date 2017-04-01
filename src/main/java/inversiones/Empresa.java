package inversiones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Empresa {

	String nombre;
	List<Periodo> periodos = new ArrayList<Periodo>();;
	List<Empresa> empresas = new ArrayList<Empresa>();;
	
	
	public void cargarDatos (String arch) throws IOException, ParseException{
		/*levantar archivo y cargar cuentas*/
		
		File archivo = new File (arch);
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		
		while((linea=br.readLine())!=null)
            parcearLinea(linea);
	}
	
	private void parcearLinea(String linea) throws ParseException{
		System.out.println(linea);
		StringTokenizer st = new StringTokenizer(linea,";");
		String empresa;
		String cuenta;
		String fInicio;
		String fFin;
		String valor;
		
		/*empresa*/
		empresa = st.nextToken();
		/*fecha inicio*/
		fInicio = st.nextToken();
		/*fecha fin*/
		fFin = st.nextToken();
		/*cuenta*/
		cuenta = st.nextToken();
		/*valor*/
		valor = st.nextToken();
		
		//ver como usar reflection
		if(empresa == "pepe"){
			//agregar logica si la empresa ya existe
		}
		else{
			Empresa emp = new Empresa();
			Periodo periodo = new Periodo();
			emp.nombre = empresa;
			periodo.agregarPeriodos(fInicio,fFin,cuenta,valor);
			emp.periodos.add(periodo);
			empresas.add(emp);
		}
		
		System.out.println (empresa+fInicio);
			
	}
	
}
