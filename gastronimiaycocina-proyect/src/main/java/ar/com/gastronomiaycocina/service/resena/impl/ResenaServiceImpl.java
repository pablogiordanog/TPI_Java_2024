package ar.com.gastronomiaycocina.service.resena.impl;

import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.entity.Participante;
import ar.com.gastronomiaycocina.entity.Resena;
import ar.com.gastronomiaycocina.service.evento.EventoService;
import ar.com.gastronomiaycocina.service.participante.ParticipanteService;
import ar.com.gastronomiaycocina.service.resena.ResenaService;
import ar.com.gastronomiaycocina.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ResenaServiceImpl implements ResenaService {

    private List<Resena> resenas = new ArrayList<>();
    private EventoService eventoService;
    private ParticipanteService participanteService;

    public ResenaServiceImpl(EventoService eventoService, ParticipanteService participanteService){
        this.setEventoService(eventoService);
        this.setParticipanteService(participanteService);
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public ParticipanteService getParticipanteService() {
        return participanteService;
    }

    public void setParticipanteService(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @Override
    public List<Resena> getResenas() {
        return resenas;
    }

    @Override
    public void listarResenias() {
        System.out.println("Lista de Reseñas");
        System.out.println("================");
        if(this.getResenas().size()==0){
            System.out.println("Aun no hay reseñas.");
        }else {
            for (Resena resena : this.getResenas()) {
                System.out.println(resena.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public boolean generarResena() {
        Scanner sc = new Scanner(System.in);
        boolean isRegistro = Boolean.FALSE;
        System.out.println("Registrar Reseña de Evento");
        System.out.println("===========================");
        UUID idEvento = Utils.ingreseUUI("Ingrese el ID del Evento:");
        Evento eventoBusco = getEventoService().getEvento(idEvento);

        UUID idParticipante = Utils.ingreseUUI("Ingrese el ID del Participante:");
        Participante participante = getParticipanteService().getParticipante(idParticipante);

        if(eventoBusco==null || participante==null) {
            if(eventoBusco==null){
                System.out.println("Evento no encontrado.");
            }
            if(participante==null) {
                System.out.println("Participante no encontrado.");
            }
        }else {
            Integer calificacion = Utils.ingreseNumero("Ingrese una calificación ", 1, 5);
            String comentario = Utils.ingreseTexto("Ingrese un comentario:", Boolean.TRUE);

            Resena resena = new Resena(UUID.randomUUID(), eventoBusco, participante, calificacion, comentario);
            this.getResenas().add(resena);
            isRegistro = Boolean.TRUE;
            System.out.println("Reseña registrada con éxito.");
        }
        System.out.println("");
        return isRegistro;
    }
}
