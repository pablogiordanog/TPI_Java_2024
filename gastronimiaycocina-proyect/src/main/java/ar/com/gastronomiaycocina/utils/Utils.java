package ar.com.gastronomiaycocina.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Utils {

    public static String ingreseTexto(String mensaje, boolean isControlTextoVacio){
        String texto = "";
        Scanner sc = new Scanner(System.in);
        while(texto.isBlank()){
            System.out.println(mensaje);
            texto = sc.nextLine();
            sc.nextLine();
            if(isControlTextoVacio && texto.isBlank()){
               System.out.println("Por favor ingrese texto.");
            }else{
                break;
            }
        }
        return texto;
    }

    public static int ingreseNumero(String mensaje, int valorInicial, int valorFinal){
        int valor = 0;
        boolean isControlarValores = Boolean.FALSE;
        String mensajeImprimir = mensaje;
        if(valorInicial>=0 && valorFinal>=valorInicial){
            isControlarValores = Boolean.TRUE;
            mensajeImprimir = mensajeImprimir.concat(" [" + valorInicial + "-" + valorFinal + "]");
        }
        Scanner sc = new Scanner(System.in);
        while(valor==0) {
            System.out.println(mensajeImprimir);
            try {
                valor = sc.nextInt();
                sc.nextLine();
                if(isControlarValores) {
                    if (valor < valorInicial || valor > valorFinal) {
                        System.out.println("Por favor respete el rango de valores permitidos.");
                        valor = 0;
                    }
                }
            }catch (Exception ex){
                System.out.println("Por favor controle los valores permitidos[solo numeros enteros]");
                sc.nextLine();
            }
        }
        return valor;
    }

    public static LocalDate ingresarFecha(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor ingrese fecha:");
        int anio = Utils.ingreseNumero("Ingrese Año XXXX:",1900,2050);
        int mes = Utils.ingreseNumero("Ingrese Mes XX:", 1, 12);
        int dia = Utils.ingreseNumero("Ingrese Día XX:", 1, 31);

        LocalDate fecha = LocalDate.of(anio,mes,dia);
        return fecha;
    }

    public static LocalTime ingresarHora(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor ingrese hora:");
        int hora = Utils.ingreseNumero("Ingrese hora:", 0, 23);
        int minutos = Utils.ingreseNumero("Ingrese minutos:", 0, 59);

        LocalTime horaIngresada = LocalTime.of(hora,minutos,0);
        return horaIngresada;
    }

    public static UUID ingreseUUI(String mensaje){
        Scanner sc = new Scanner(System.in);
        String texto = "";
        UUID id = null;
        while(texto.isBlank()){
            System.out.println(mensaje);
            texto = sc.nextLine();
            sc.nextLine();
            if(texto.isBlank()){
                System.out.println("Por favor ID!");
            }else{
                try{
                    id = UUID.fromString(texto);
                    break;
                }catch (Exception ex){
                    texto = "";
                }
            }
        }
        return id;
    }

    public static String getPathUser(){
        return System.getProperty("user.dir");
    }

    public static String getPathRecursos(){
        String pathRecursos = getPathUser().concat("/src/main/java/ar/com/gastronomiaycocina/recursos");
        return pathRecursos;
    }
}
