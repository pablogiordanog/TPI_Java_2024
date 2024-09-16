package ar.com.gastronomiaycocina.service.evento.impl;

import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.entity.Participante;
import ar.com.gastronomiaycocina.service.archivo.ArchivoService;
import ar.com.gastronomiaycocina.service.archivo.impl.ArchivoServiceImpl;
import ar.com.gastronomiaycocina.service.evento.EventoService;
import ar.com.gastronomiaycocina.utils.Utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class EventoServiceImpl implements EventoService {
    private List<Evento> eventos = new ArrayList<>();

    @Override
    public Evento registrarEvento() {
        UUID id = UUID.randomUUID();
        System.out.println("Registrar un nuevo Evento");
        System.out.println("=========================");
        String nombre = Utils.ingreseTexto("Ingrese Nombre de Evento", Boolean.TRUE);
        String descripcion = Utils.ingreseTexto("Ingrese Descripción del Evento:", Boolean.FALSE);

        System.out.println("Ingrese la Fecha-Hora del Evento:");
        LocalDate fecha = Utils.ingresarFecha();
        LocalTime hora = Utils.ingresarHora();
        LocalDateTime fechaHora = LocalDateTime.of(fecha,hora);

        String ubicacion = Utils.ingreseTexto("Ingrese la Ubicación:", Boolean.TRUE);
        int capacidad = Utils.ingreseNumero("Ingrese la capacidad máxima del evento:", 1, 100);

        Evento evento = new Evento(id,nombre,descripcion,fechaHora,ubicacion,capacidad, new HashMap<>());

        this.getEventos().add(evento);
        System.out.println("Evento Registrado con éxito.");
        System.out.println("");
        return evento;
    }

    @Override
    public List<Evento> getEventos() {
        return this.eventos;
    }

    @Override
    public Evento getEvento(UUID id) {
        List<Evento> eventos = new LinkedList<>(getEventos());
        Evento e = null;
        for(Evento evento:eventos){
            if(evento.getId().equals(id)){
                e = evento;
                break;
            }
        }
        return e;
    }

    @Override
    public void listarEventos() {
        System.out.println("Lista de Eventos");
        System.out.println("================");
        if(getEventos().size()==0){
            System.out.println("Aun no hay eventos.");
        }else {
            for (Evento evento : getEventos()) {
                System.out.println(evento.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public void listarEventos(List<Evento> list) {
        if(list.size()==0){
            System.out.println("Aun no hay eventos.");
        }else {
            for (Evento evento : list) {
                System.out.println(evento.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public boolean registrarParticipanteAEvento(Evento evento, Participante participante) {
        boolean isRegistrarParticipante = Boolean.FALSE;
        if(evento.getParticipantes().equals(participante)){
            System.out.println("El participante ya se encuentra en el evento.");
        }else{
            if(evento.esCapacidadMaxima()){
                System.out.println("Se alcanzo la capacidad máxima del evento.");
                System.out.println("No se ha inscripto al participante.");
            }else{
                evento.getParticipantes().put(participante.getId(), participante);
                isRegistrarParticipante = Boolean.TRUE;
            }
        }
        return isRegistrarParticipante;
    }

    @Override
    public List<Evento> listadoEventosDisponibles() {
        System.out.println("Lista de Eventos Disponibles");
        System.out.println("============================");
        LocalDate fecha = Utils.ingresarFecha();

        List<Evento> eventosDisponibles = new ArrayList<>();
        for(Evento evento:this.getEventos()){
            LocalDate fechaEvento = LocalDate.of(evento.getFechaHora().getYear(),
                                    evento.getFechaHora().getMonthValue(),
                                    evento.getFechaHora().getDayOfMonth());

            if(fecha.equals(fechaEvento)
                    || fecha.isBefore(fechaEvento)){
                //Si es igual fecha a fecha de evento o si es antes la fecha a la del eveto
                if(!evento.esCapacidadMaxima()){
                    eventosDisponibles.add(evento);
                }
            }
        }
        System.out.println("============================");
        this.listarEventos(eventosDisponibles);
        return eventosDisponibles;
    }

    @Override
    public List<Evento> listadoEventosNoDisponibles() {
        System.out.println("Lista de Eventos No Disponibles");
        System.out.println("===============================");
        LocalDate fecha = Utils.ingresarFecha();

        List<Evento> eventosNoDisponibles = new ArrayList<>();
        for(Evento evento:this.getEventos()){
            LocalDate fechaEvento = LocalDate.of(evento.getFechaHora().getYear(),
                    evento.getFechaHora().getMonthValue(),
                    evento.getFechaHora().getDayOfMonth());

            if(fecha.equals(fechaEvento)
                    || fecha.isBefore(fechaEvento)){
                //Si es igual fecha a fecha de evento o si es antes la fecha a la del eveto
                if(evento.esCapacidadMaxima()){
                    eventosNoDisponibles.add(evento);
                }
            }
        }
        System.out.println("===============================");
        this.listarEventos(eventosNoDisponibles);
        return eventosNoDisponibles;
    }

    @Override
    public boolean exportarArchivoDeEventos() {
        boolean isExportoArchivo = Boolean.FALSE;
        List<Evento> eventosNoDisponibles = listadoEventosNoDisponibles();
        if(eventosNoDisponibles.size()>0){
            String pathName = Utils.getPathRecursos().concat("/eventos.csv");
            ArchivoService archivoService = new ArchivoServiceImpl();
            isExportoArchivo = archivoService.exportarCsv(pathName, eventosNoDisponibles);
        }
        return isExportoArchivo;
    }
}
