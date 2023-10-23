package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departamento {
	
	Integer id;
	String nombre;
	Empleado jefe;
	
}
