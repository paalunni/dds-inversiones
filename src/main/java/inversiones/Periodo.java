package inversiones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Periodo {

	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	Date fechaComienzo;
	Date fechaFin;
	
	void agregarPeriodos(String fInicio,String fFin, String cuenta, String valor) throws ParseException{
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		//ver
		if(cuenta == "pepe"){
			//agregar logica si la cuenta ya existe
		}
		else{
			fechaComienzo = formatoDeFecha.parse(fInicio);
			fechaFin = formatoDeFecha.parse(fFin);
			Cuenta cuen = new Cuenta();
			cuen.agregarValores(cuenta, valor);
			cuentas.add(cuen);
		}
	}
	
}
