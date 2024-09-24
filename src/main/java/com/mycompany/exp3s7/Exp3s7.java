/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp3s7;
/**
 *
 * @author Aleja
 */
import java.util.ArrayList;
import java.util.Scanner;

class Entrada {
    // Variables de instancia
    int numero;
    String ubicacion;
    double precio;
    String tipoDescuento;
    double descuento;
    double precioFinal;

    // Constructor
    public Entrada(int numero, String ubicacion, double precio, String tipo, double descuento) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.tipoDescuento = tipo;
        this.descuento = descuento;
        calcularPrecioFinal();
    }

    // Método para calcular el precio final aplicando el descuento
    private void calcularPrecioFinal() {
        this.precioFinal = precio - (precio * descuento / 100);
    }

    // Método para mostrar la información de la entrada
    public void mostrarInformacion() {       
        System.out.println("--------------------------------");
        System.out.println("-----------TEATRO MORO----------");
        System.out.println("--------------------------------");
        System.out.println("|Número de asiento: " + numero);
        System.out.println("|Sector: " + ubicacion);
        System.out.println("|Precio: $" + precio);
        System.out.println("|Tipo descuento: " + tipoDescuento);
        System.out.println("|Porcentaje descuento: " + ((int)descuento) + "%");
        System.out.println("|Precio Final: $" + precioFinal);
        System.out.println("--------------------------------");
        System.out.println("---!Que disfrutes la función!---");
        System.out.println("--------------------------------");
    }
}

public class Exp3s7 {
    
    static ArrayList<Entrada> entradas = new ArrayList<>();
    static boolean[] asientosOcupados = new boolean[60];
    
    public static void main(String[] args) {
        
    Scanner scanner = new Scanner(System.in);
    int opcion;
        
        do {
            System.out.println("\nMenú:\n");
            System.out.println("1. Comprar una entrada");
            System.out.println("2. Pagar");
            System.out.print("\nSelecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ventaDeEntradas(scanner);
                    break;
                case 2:
                    pagarEntradas();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
        
        
    }
    
    public static void ventaDeEntradas(Scanner scanner) {
        System.out.println("\nElige una ubicación:");
        System.out.println("1. VIP (1-10) - Precio: $100");
        System.out.println("2. Platea (11-30) - Precio: $50");
        System.out.println("3. General (31-60) - Precio: $30");
        System.out.println("\n4. Volver al menú principal");

        int opcionUbicacion = scanner.nextInt();
        if (opcionUbicacion == 4) return;

        String ubicacion = "";
        double precio = 0;
        int inicio = 0, fin = 0;

        switch (opcionUbicacion) {
            case 1:
                ubicacion = "VIP";
                precio = 100;
                inicio = 1;
                fin = 10;
                break;
            case 2:
                ubicacion = "Platea";
                precio = 50;
                inicio = 11;
                fin = 30;
                break;
            case 3:
                ubicacion = "General";
                precio = 30;
                inicio = 31;
                fin = 60;
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Mostrar los asientos disponibles en la ubicación seleccionada
        System.out.println("\nAsientos disponibles: ");
        for (int i = inicio; i <= fin; i++) {
            if (!asientosOcupados[i - 1]) {
                System.out.print(i + " ");
            }
        }
        
        System.out.println();

        int numeroAsiento = scanner.nextInt();
        if (numeroAsiento < inicio || numeroAsiento > fin || asientosOcupados[numeroAsiento - 1]) {
            System.out.println("Número de asiento no válido o ya está ocupado.");
            return;
        }

        // Solicitar el tipo de entrada
        System.out.println("\nSelecciona el tipo de entrada:");
        System.out.println("1. Público General (sin descuento)");
        System.out.println("2. Estudiante (10% descuento)");
        System.out.println("3. Tercera Edad (15% descuento)");
        System.out.println("\n4. Volver al menú principal");

        int opcionTipo = scanner.nextInt();
        if (opcionTipo == 4) return;

        String tipo = "";
        double descuento = 0;

        switch (opcionTipo) {
            case 1:
                tipo = "Público General";
                descuento = 0;
                break;
            case 2:
                tipo = "Estudiante";
                descuento = 10;
                break;
            case 3:
                tipo = "Tercera Edad";
                descuento = 15;
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        asientosOcupados[numeroAsiento - 1] = true;
        Entrada entrada = new Entrada(numeroAsiento, ubicacion, precio, tipo, descuento);
        entradas.add(entrada);
        entrada.mostrarInformacion();
    }

    public static void pagarEntradas() {

        int costeTotal = 0;
        int contadorEntradas = 0;
        double dineroAhorrado = 0;

        for (Entrada entrada : entradas){

            contadorEntradas++;

            costeTotal = costeTotal + ((int)entrada.precioFinal);

            dineroAhorrado = dineroAhorrado + (entrada.precio - entrada.precioFinal);

            System.out.println("Entrada número: " + contadorEntradas);
            entrada.mostrarInformacion();

        }

        System.out.println("-----------TEATRO MORO----------");
        System.out.println("--------------------------------");
        System.out.println("Entradas vendidas: " + contadorEntradas);
        System.out.println("Total a pagar: $" + costeTotal);
        System.out.println("Dinero ahorrado: $" + dineroAhorrado);
        System.out.println("--------------------------------");
        System.out.println("--------!Suerte y éxito!--------");
        System.out.println("--------------------------------");
        
    }
}

