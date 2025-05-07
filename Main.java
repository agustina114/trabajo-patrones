import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Credencial plantilla = new Credencial("Nombre", "Cargo", "RUT");
        GestorCredenciales gestor = GestorCredenciales.getInstancia();

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar nueva credencial");
            System.out.println("2. Ver credenciales generadas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingresa un número: ");
                sc.next(); 
            }
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    System.out.print("RUT: ");
                    String rut = sc.nextLine();

                    Credencial nueva = plantilla.clone();
                    nueva.setNombre(nombre);
                    nueva.setCargo(cargo);
                    nueva.setRut(rut);
                    gestor.agregarCredencial(nueva);
                    System.out.println("¡Credencial generada con éxito!");
                    break;

                case 2:
                    gestor.mostrarCredenciales();
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);

        sc.close();
    }
}


class Credencial implements Cloneable {
    private String nombre;
    private String cargo;
    private String rut;

    public Credencial(String nombre, String cargo, String rut) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void mostrar() {
        System.out.println("\n----- CREDENCIAL -----");
        System.out.println("Nombre: " + nombre);
        System.out.println("Cargo : " + cargo);
        System.out.println("RUT   : " + rut);
        System.out.println("----------------------");
    }

    @Override
    public Credencial clone() {
        try {
            return (Credencial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}


class GestorCredenciales {
    private static GestorCredenciales instancia;
    private List<Credencial> listaCredenciales;

    private GestorCredenciales() {
        listaCredenciales = new ArrayList<>();
    }

    public static GestorCredenciales getInstancia() {
        if (instancia == null) {
            instancia = new GestorCredenciales();
        }
        return instancia;
    }

    public void agregarCredencial(Credencial c) {
        listaCredenciales.add(c);
    }

    public void mostrarCredenciales() {
        if (listaCredenciales.isEmpty()) {
            System.out.println("No hay credenciales generadas.");
        } else {
            for (Credencial c : listaCredenciales) {
                c.mostrar();
            }
        }
    }
}
