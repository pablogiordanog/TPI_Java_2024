package ar.com.gastronomiaycocina.service.archivo.impl;

import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.service.archivo.ArchivoService;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoServiceImpl implements ArchivoService {
    @Override
    public boolean exportarCsv(String pathName, List<Evento> eventosList) {
        boolean isOkExportacion = Boolean.FALSE;
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(pathName));
            String[] encabezado = {"ID", "NOMBRE","DESCRIPCION", "CAPACIDAD","FECHA","HORA","NRO PARTICIPANTES"};
            csvWriter.writeNext(encabezado);
            for (Evento evento:eventosList){
                csvWriter.writeNext(evento.getDato());
            }
            csvWriter.close();
            isOkExportacion = Boolean.TRUE;
        }catch (IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }finally {
            return isOkExportacion;
        }
    }
}
