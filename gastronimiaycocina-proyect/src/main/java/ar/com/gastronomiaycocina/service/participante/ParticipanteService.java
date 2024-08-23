package ar.com.gastronomiaycocina.service.participante;

import ar.com.gastronomiaycocina.entity.Participante;
import ar.com.gastronomiaycocina.entity.Resena;
import ar.com.gastronomiaycocina.service.evento.EventoService;

import java.util.List;
import java.util.UUID;

public interface ParticipanteService {
    List<Participante> getParticipantes();
    boolean registrarInscripcion(EventoService evento);

    List<Participante> getListaUnicaParticipantesEnEventos();
    void listaDeParticipantes();
    Participante getParticipante(UUID id);
}
