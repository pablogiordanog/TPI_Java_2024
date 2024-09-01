package ar.com.gastronomiaycocina.service.evento;

import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.entity.Participante;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventoService {
    Evento registrarEvento();
    List<Evento> getEventos();
    Evento getEvento(UUID id);
    void listarEventos();
    void listarEventos(List<Evento> list);
    boolean registrarParticipanteAEvento(Evento evento, Participante participante);
    List<Evento> listadoEventosDisponibles();
    List<Evento> listadoEventosNoDisponibles();
    boolean exportarArchivoDeEventos();
}
