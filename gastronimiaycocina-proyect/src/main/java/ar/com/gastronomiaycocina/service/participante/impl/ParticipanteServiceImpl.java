package ar.com.gastronomiaycocina.service.participante.impl;

import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.entity.Participante;
import ar.com.gastronomiaycocina.entity.Resena;
import ar.com.gastronomiaycocina.enumeration.InteresCulinario;
import ar.com.gastronomiaycocina.service.evento.EventoService;
import ar.com.gastronomiaycocina.service.participante.ParticipanteService;
import ar.com.gastronomiaycocina.service.resena.ResenaService;

import java.util.*;

public class ParticipanteServiceImpl implements ParticipanteService {

    private List<Participante> participantes = new ArrayList<>();
    private EventoService eventoService;

    public ParticipanteServiceImpl(EventoService eventoService){
        this.setEventoService(eventoService);
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Override
    public List<Participante> getParticipantes() {
        return this.participantes;
    }

    @Override
    public boolean registrarInscripcion(EventoService evento) {
        Scanner sc = new Scanner(System.in);
        boolean isRegistro = Boolean.FALSE;
        System.out.println("Inscripción de Participante");
        System.out.println("============================");
        System.out.println("Ingrese el ID del Evento:");
        String idEvento = sc.nextLine();
        sc.nextLine();
        Evento eventoBusco = evento.getEvento(UUID.fromString(idEvento));
        if(eventoBusco!=null){
            System.out.println("Ingrese el Nombre y Apellido del Participante");
            String nombre = sc.nextLine();
            sc.nextLine();

            System.out.println("Cual es su interes Culinario? elija una opción:");
            int opcion = 1;
            for(InteresCulinario i:InteresCulinario.values()){
                System.out.println(opcion + ". " +i.name());
                opcion++;
            }
            int opcionSel = sc.nextInt();
            sc.nextLine();

            InteresCulinario interes;
            switch (opcionSel){
                case 1 -> interes = InteresCulinario.COMIDA_ITALIANA;
                case 2 -> interes = InteresCulinario.COMIDA_MEXICANA;
                case 3 -> interes = InteresCulinario.COMIDA_VEGANA;
                default -> interes = InteresCulinario.NINGUNA;
            };

            Participante participante = new Participante(UUID.randomUUID(), nombre, interes);
            if(evento.registrarParticipanteAEvento(eventoBusco, participante)){
                this.getParticipantes().add(participante);
                System.out.println("Participante Inscripto con éxito!");
                System.out.println("");
                isRegistro = Boolean.TRUE;
            }
        }else{
            System.out.println("Código de Evento incorrecto!");
        }
        return isRegistro;
    }


    @Override
    public List<Participante> getListaUnicaParticipantesEnEventos() {
        Set<Participante> listasParticipantesSinRepetir = new HashSet<>();
        for(Evento evento:this.getEventoService().getEventos()){
            //Esto para generar una lista sin estudiantes repetidos
            listasParticipantesSinRepetir.addAll(evento.getParticipantes().values());
        }
        ArrayList<Participante> listaParticipantes = new ArrayList<Participante>(listasParticipantesSinRepetir);
        return listaParticipantes;
    }

    @Override
    public void listaDeParticipantes() {
        List<Participante> listParticipantes = new ArrayList<>(getListaUnicaParticipantesEnEventos());

        System.out.println("Lista de Participantes");
        System.out.println("======================");
        if(listParticipantes.size()==0){
            System.out.println("Aun no hay participantes.");
        }else {
            for (Participante participante : listParticipantes) {
                System.out.println(participante.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public Participante getParticipante(UUID id) {
        List<Participante> listParticipantesEnEventos = new LinkedList<>(getListaUnicaParticipantesEnEventos());
        Participante participanteReturn = null;
        for(Participante participante:listParticipantesEnEventos){
            if(participante.getId().equals(id)){
                participanteReturn = participante;
                break;
            }
        }
        return participanteReturn;
    }
}
