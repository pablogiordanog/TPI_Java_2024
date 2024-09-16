package ar.com.gastronomiaycocina.service.chef.impl;

import ar.com.gastronomiaycocina.entity.Chef;
import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.enumeration.EspecialidadEnum;
import ar.com.gastronomiaycocina.service.chef.ChefService;
import ar.com.gastronomiaycocina.service.evento.EventoService;
import ar.com.gastronomiaycocina.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ChefServiceImpl implements ChefService {
    private List<Chef> chefs = new ArrayList<>();

    public ChefServiceImpl(EventoService eventoService){
        this.setEventoService(eventoService);
    }

    private EventoService eventoService;

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Override
    public Chef registrarChef() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Registrar Chef");
        System.out.println("==============");

        String nombre = Utils.ingreseTexto("Ingrese el Nombre del Chef", Boolean.TRUE);

        System.out.println("Cual es su Especialidad? elija una opción:");
        int opcion = 1;
        for(EspecialidadEnum e: EspecialidadEnum.values()){
            System.out.println(opcion + ". " +e.name());
            opcion++;
        }
        int opcionSel = sc.nextInt();
        sc.nextLine();

        EspecialidadEnum especialidad;
        switch (opcionSel){
            case 1 -> especialidad = EspecialidadEnum.PASTELERIA;
            case 2 -> especialidad = EspecialidadEnum.PASTELERIA;
            case 3 -> especialidad = EspecialidadEnum.NINGUNA;
            default -> especialidad = EspecialidadEnum.NINGUNA;
        };

        Chef chef = new Chef(UUID.randomUUID(),nombre, especialidad);

        this.getChefts().add(chef);

        System.out.println("Chef ingresdo con éxito!");
        System.out.println("");

        return chef;
    }

    @Override
    public Chef getChef(UUID id) {
        Chef chefResult = null;
        for(Chef chef:this.getChefts()){
            if(chef.getId().equals(id)){
                chefResult = chef;
                break;
            }
        }
        return chefResult;
    }

    @Override
    public boolean asignarChefAEvento() {
        boolean isAsignado = Boolean.FALSE;
        Scanner sc = new Scanner(System.in);
        System.out.println("Asignación de Chef a Evento");
        System.out.println("===========================");

        UUID idChef = Utils.ingreseUUI("Ingrese el ID del Chef");
        Chef chef = this.getChef(idChef);
        if(chef!=null){
            UUID idEvento = Utils.ingreseUUI("Ingrese el ID de Evento");
            Evento evento = this.getEventoService().getEvento(idEvento);
            if(evento!=null){
                evento.setChef(chef);
                System.out.println("Asignación de Chef a Evento exitosa!");
                isAsignado = Boolean.TRUE;
            }else{
                System.out.println("Evento no encontrado");
            }
        }else{
            System.out.println("Chef no encontrado.");
        }
        System.out.println("");
        return isAsignado;
    }

    @Override
    public List<Chef> getChefts() {
        return this.chefs;
    }

    @Override
    public void listarChefts() {
        System.out.println("Lista de Chefts");
        System.out.println("===============");
        if(this.getChefts().size()==0){
            System.out.println("Aun no hay chef cargados.");
        }else {
            for (Chef chef : this.getChefts()) {
                System.out.println(chef.toString());
            }
        }
        System.out.println("");
    }

    @Override
    public List<Evento> eventosParticipa(Chef chef) {
        ArrayList<Evento> eventosParticipa = new ArrayList<>();
        for(Evento evento:this.getEventoService().getEventos()){
            if(evento.getChef().equals(chef)){
                eventosParticipa.add(evento);
            }
        }
        return eventosParticipa;
    }
}
