package ar.com.gastronomiaycocina.service.evento.impl;

import ar.com.gastronomiaycocina.entity.Chef;
import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.entity.Participante;
import ar.com.gastronomiaycocina.service.evento.EventoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class EventoServiceImpl implements EventoService {
    private List<Evento> eventos = new ArrayList<>();

    @Override
    public Evento registrarEvento() {
        Scanner sc = new Scanner(System.in);
        UUID id = UUID.randomUUID();
        System.out.println("Registrar un nuevo Evento");
        System.out.println("=========================");
        System.out.println("Ingrese Nombre de Evento:");
        String nombre = sc.nextLine();
        sc.nextLine();

        System.out.println("Ingrese Descripción del Evento:");
        String descripcion = sc.nextLine();
        sc.nextLine();

        System.out.println("Ingrese la Fecha-Hora del Evento:");
        System.out.print("Ingrese el Año:");
        int year = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el Mes:");
        int month = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el Día:");
        int dayOfMoth = sc.nextInt();
        sc.nextLine();

        LocalDate fecha = LocalDate.of(year,month,dayOfMoth);

        System.out.print("Ahora Ingrese la Hora:");
        int hour = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese los minutos:");
        int minute = sc.nextInt();
        sc.nextLine();

        LocalTime hora = LocalTime.of(hour,minute,0,0);
        LocalDateTime fechaHora = LocalDateTime.of(fecha,hora);

        System.out.println("Ingrese la Ubicación:");
        String ubicacion = sc.nextLine();
        sc.nextLine();

        System.out.print("Ingrese la capacidad del curso:");
        Integer capacidad = sc.nextInt();
        sc.nextLine();

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
        System.out.println("Lista de Eventos");
        System.out.println("================");
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
            if(evento.isLlegoACapacidadMaxima()){
                System.out.println("Se alcanzo la capacidad máxima del evento.");
            }else{
                evento.getParticipantes().put(participante.getId(), participante);
                System.out.println("Participante registrado en evento.");
                isRegistrarParticipante = Boolean.TRUE;
            }
        }
        return isRegistrarParticipante;
    }

    @Override
    public List<Evento> listadoEventosDisponibles(LocalDate fecha) {
        List<Evento> eventosDisponibles = new ArrayList<>();
        for(Evento evento:this.getEventos()){
            LocalDate fechaEvento = LocalDate.of(evento.getFechaHora().getYear(),
                                    evento.getFechaHora().getMonthValue(),
                                    evento.getFechaHora().getDayOfMonth());

            if(fecha.equals(fechaEvento)
                    || fecha.isBefore(fechaEvento)){
                //Si es igual fecha a fecha de evento o si es antes la fecha a la del eveto
                if(!evento.isLlegoACapacidadMaxima()){
                    eventosDisponibles.add(evento);
                }
            }
        }
        this.listarEventos(eventosDisponibles);
        return eventosDisponibles;
    }

    @Override
    public List<Evento> listadoEventosNoDisponibles(LocalDate fecha) {
        List<Evento> eventosNoDisponibles = new ArrayList<>();
        for(Evento evento:this.getEventos()){
            LocalDate fechaEvento = LocalDate.of(evento.getFechaHora().getYear(),
                    evento.getFechaHora().getMonthValue(),
                    evento.getFechaHora().getDayOfMonth());

            if(fecha.equals(fechaEvento)
                    || fecha.isBefore(fechaEvento)){
                //Si es igual fecha a fecha de evento o si es antes la fecha a la del eveto
                if(evento.isLlegoACapacidadMaxima()){
                    eventosNoDisponibles.add(evento);
                }
            }
        }
        this.listarEventos(eventosNoDisponibles);
        return eventosNoDisponibles;
    }

    @Override
    public boolean exportarArchivoDeEventos(LocalDate fecha) {
        return false;
    }
}
