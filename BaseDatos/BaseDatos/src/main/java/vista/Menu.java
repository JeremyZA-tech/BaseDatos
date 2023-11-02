package vista;

import java.util.Random;

import controlador.Empresa;
import io.IO;
import modelo.Departamento;
import modelo.Empleado;

public class Menu {

	private Empresa empresa;

	
    public Menu(Empresa empresa) {
		super();
		this.empresa = empresa;
	}

	public void mostrarMenu() {


        while (true) {
            System.out.println("Seleccione una tabla:");
            System.out.println("1. Departamento");
            System.out.println("2. Empleado");
            System.out.println("0. Salir");

            int opcion = IO.readInt();

            if (opcion == 0) {
                break;
            }

            switch (opcion) {
                case 1:
                    menuDepartamento();
                    break;
                case 2:
                    menuEmpleado();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        System.out.println("Saliendo del programa.");
    }

    private void menuDepartamento() {

        while (true) {
            System.out.println("Menú Departamento:");
            System.out.println("1. Añadir Departamento");
            System.out.println("2. Mostrar Tabla Departamento");
            System.out.println("3. Eliminar Dato de la Tabla Departamento");
            System.out.println("4. Actualizar Dato de la Tabla Departamento");
            System.out.println("0. Volver al Menú Principal");

            int opcion = IO.readInt();

            if (opcion == 0) {
                break;
            }

            switch (opcion) {
                case 1:
                	añadirDepartamento(empresa);
                	break;
                case 2:
                	mostrarDepartamentos(empresa);
                	break;
                case 3:

                	break;
                case 4:
                	
                	break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void menuEmpleado() {

        while (true) {
            System.out.println("Menú Empleado:");
            System.out.println("1. Añadir Empleado");
            System.out.println("2. Mostrar Tabla Empleado");
            System.out.println("3. Eliminar Dato de la Tabla Empleado");
            System.out.println("4. Actualizar Dato de la Tabla Empleado");
            System.out.println("0. Volver al Menú Principal");

            int opcion = IO.readInt();

            if (opcion == 0) {
                break;
            }

            switch (opcion) {
                case 1:

                	break;
                case 2:

                	break;
                case 3:

                	break;
                case 4:

                	break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
    
    private static void añadirDepartamento(Empresa empresa) {
    	IO.print("¿Desea añadir el departamento con jefe (1) o sin jefe (2)?");
        int opcion = IO.readInt();

        if (opcion == 1) {
            // Añadir el departamento con jefe
            IO.print("Nombre del departamento: ");
            String nombre = IO.readString();
            IO.print("Nombre del Jefe de departamento: ");
            String nombreJefe = IO.readString();
            if (nombreJefe.isEmpty()) {
                IO.print("El nombre del jefe no puede estar vacío");
                return;
            }
            Empleado jefe = new Empleado(nombreJefe);

            // Generar un id aleatorio
            Random random = new Random();
            int id = random.nextInt(10000);

            // Añadir el departamento a la empresa
            boolean anadido = empresa.addDepartamento(new Departamento(id, nombre, jefe));
            IO.print(anadido ? "Añadido" : "No se ha podido añadir");
        } else if (opcion == 2) {
            // Añadir el departamento sin jefe
            IO.print("Nombre del departamento: ");
            String nombre = IO.readString();

            // Generar un id aleatorio
            Random random = new Random();
            int id = random.nextInt(10000);

            // Añadir el departamento a la empresa
            boolean anadido = empresa.addDepartamento(new Departamento(id, nombre));
            IO.print(anadido ? "Añadido\n" : "No se ha podido añadir\n");
        } else {
            IO.print("Opción incorrecta");
        }
    }
    
    private static void mostrarDepartamentos(Empresa empresa) {
    	System.out.println(empresa.showDepart());
    }
}
