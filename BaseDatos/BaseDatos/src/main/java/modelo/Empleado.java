package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {
	
	Integer id;
	String nombre;
	Double salario;
	Departamento departamento;

}
