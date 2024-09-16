package ar.com.gastronomiaycocina.service.participante;

import ar.com.gastronomiaycocina.entity.Participante;
import java.util.List;
import java.util.UUID;

public interface ParticipanteService {
    List<Participante> getParticipantes();
    boolean registrarInscripcion();
    void listaDeParticipantes();
    Participante getParticipante(UUID id);
}
