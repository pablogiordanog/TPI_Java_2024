package ar.com.gastronomiaycocina.service.chef;

import ar.com.gastronomiaycocina.entity.Chef;
import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.service.evento.EventoService;

import java.util.List;
import java.util.UUID;

public interface ChefService {

    Chef registrarChef();
    Chef getChef(UUID id);
    boolean asignarChefAEvento();
    List<Chef> getChefts();
    void listarChefts();
    List<Evento> eventosParticipa(Chef chef);
}
