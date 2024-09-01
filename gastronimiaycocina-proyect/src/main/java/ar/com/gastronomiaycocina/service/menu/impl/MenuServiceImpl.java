package ar.com.gastronomiaycocina.service.menu.impl;

import ar.com.gastronomiaycocina.service.chef.ChefService;
import ar.com.gastronomiaycocina.service.chef.impl.ChefServiceImpl;
import ar.com.gastronomiaycocina.service.evento.EventoService;
import ar.com.gastronomiaycocina.service.evento.impl.EventoServiceImpl;
import ar.com.gastronomiaycocina.service.menu.MenuService;
import ar.com.gastronomiaycocina.service.participante.ParticipanteService;
import ar.com.gastronomiaycocina.service.participante.impl.ParticipanteServiceImpl;
import ar.com.gastronomiaycocina.service.resena.ResenaService;
import ar.com.gastronomiaycocina.service.resena.impl.ResenaServiceImpl;

import java.util.Scanner;

public class MenuServiceImpl implements MenuService {
    @Override
    public void mostrarMenu(Scanner scanner) {
        EventoService eventos = new EventoServiceImpl();
        ParticipanteService participantes = new ParticipanteServiceImpl(eventos);
        ResenaService resenias = new ResenaServiceImpl(eventos, participantes);
        ChefService chefs = new ChefServiceImpl(eventos);
        int opcion;
        do {
            System.out.println("Ingrese opcion:");
            System.out.println("--------------");
            System.out.println("1. Crear un nuevo Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Inscripción de Participantes");
            System.out.println("4. Listado de Participantes");
            System.out.println("5. Registrar Chef");
            System.out.println("6. Listar Chefts");
            System.out.println("7. Asignar Chef a un Evento");
            System.out.println("8. Agendar Reseña de Evento");
            System.out.println("9. Listar Reseñas");
            System.out.println("10.Listado de Eventos Disponibles");
            System.out.println("11.Listado de Eventos NO Disponibles");
            System.out.println("12.Generar Archivo Csv con Eventos No Disponibles");
            System.out.println("13. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); //Limpiar salto de linea

            switch (opcion){
                case 1:
                    eventos.registrarEvento();
                    break;
                case 2:
                    eventos.listarEventos();
                    break;
                case 3:
                    participantes.registrarInscripcion(eventos);
                    break;
                case 4:
                    participantes.listaDeParticipantes();
                    break;
                case 5:
                    chefs.registrarChef();
                    break;
                case 6:
                    chefs.listarChefts();
                    break;
                case 7:
                    chefs.asignarChefAEvento();
                    break;
                case 8:
                    resenias.generarResena();
                    break;
                case 9:
                    resenias.listarResenias();
                    break;
                case 10:
                    eventos.listadoEventosDisponibles();
                    break;
                case 11:
                    eventos.listadoEventosNoDisponibles();
                    break;
                case 12:
                    eventos.exportarArchivoDeEventos();
                    break;
                default:
                    System.out.println("Opción de menú invalida.");
            }
        }while(opcion!=13);
    }
}
