package ar.com.gastronomiaycocina.service.archivo;

import ar.com.gastronomiaycocina.entity.Evento;

import java.util.List;

public interface ArchivoService {

    boolean exportarCsv(String pathName, List<Evento> eventosList);

}
