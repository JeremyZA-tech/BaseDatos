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
                	eliminarDepartamento(empresa);
                	break;
                case 4:
                	actualizarDepartamento(empresa);
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
            	añadirEmpleado(empresa);
            	break;
            case 2:
            	mostrarEmpleados(empresa);
            	break;
            case 3:
            	eliminarEmpleado(empresa);
            	break;
            case 4:
            	actualizarEmpleado(empresa);
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
	
    private static void eliminarDepartamento(Empresa empresa, Int departamentoId) {
        if (empresa.deleteDepartamento(departamentoId)) {
            System.out.println("El departamento se eliminó sin problemas.");
        } else {
            System.out.println("Ha ocurrido un error al eliminar el departamento");
        }
    }

        private static void actualizarDepartamento(Empresa empresa) {
    	
    	IO.print("Ingresa el ID del departamento que desea actualizar: ");
        int idDepartamento = IO.readInt();
        IO.print("Ingresa el nuevo nombre del departamento: ");
        String nuevoNombre = IO.readString();
        
        Departamento departamentoActualizado = new Departamento();
        departamentoActualizado.setId(idDepartamento);
        departamentoActualizado.setNombre(nuevoNombre); // Establece el nuevo nombre proporcionado por el usuario
        
        boolean actualizado = empresa.updateDepartamento(departamentoActualizado);
        
        if (actualizado) {
            IO.print("El departamento se ha actualizado.");
        } else {
            IO.print("No se pudo actualizar.");
        }
    }
	
    private static void añadirEmpleado(Empresa empresa) {}
    private static void mostrarEmpleados(Empresa empresa) {}

    private static void eliminarEmpleado(Empresa empresa, Int empleadoId) {
    	
        boolean empleadoEliminado = empresa.deleteEmpleado(empleadoId);

        if (empleadoEliminado) {
            System.out.println("Se ha eliminado al empleado con éxito.");
        } else {
            System.out.println("No se ha podido eliminar al empleado.");
        }
    }

    private static void actualizarEmpleado(Empresa empresa) {
	IO.print("Ingrese el ID del empleado que desea actualizar: ");
        int idEmpleado = IO.readInt();
        
        IO.print("Ingrese el nuevo nombre del empleado: ");
        String nuevoNombre = IO.readString();
        
        IO.print("Ingrese el nuevo salario del empleado: ");
        double nuevoSalario = IO.readDouble();
        
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setId(idEmpleado);
        empleadoActualizado.setNombre(nuevoNombre);
        empleadoActualizado.setSalario(nuevoSalario);
        
        boolean actualizado = empresa.updateEmpleado(empleadoActualizado);
        
        if (actualizado) {
            IO.print("Los datos del empleado se actualizaron con exito.");
        } else {
            IO.print("No se ha podido actualizar el empleado.");
        }
    }
	
}
