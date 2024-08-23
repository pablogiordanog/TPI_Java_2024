package ar.com.gastronomiaycocina.service.resena;

import ar.com.gastronomiaycocina.entity.Resena;

import java.util.List;

public interface ResenaService {

    boolean generarResena();
    List<Resena> getResenas();
    void listarResenias();
}
