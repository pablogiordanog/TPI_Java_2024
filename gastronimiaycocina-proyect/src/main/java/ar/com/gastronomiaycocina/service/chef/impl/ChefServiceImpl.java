package ar.com.gastronomiaycocina.service.chef.impl;

import ar.com.gastronomiaycocina.entity.Chef;
import ar.com.gastronomiaycocina.entity.Evento;
import ar.com.gastronomiaycocina.enumeration.Especialidad;
import ar.com.gastronomiaycocina.service.chef.ChefService;
import ar.com.gastronomiaycocina.service.evento.EventoService;

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

        System.out.println("Ingrese el Nombre del Chef");
        String nombre = sc.nextLine();
        sc.nextLine();

        System.out.println("Cual es su Especialidad? elija una opción:");
        int opcion = 1;
        for(Especialidad e:Especialidad.values()){
            System.out.println(opcion + ". " +e.name());
            opcion++;
        }
        int opcionSel = sc.nextInt();
        sc.nextLine();

        Especialidad especialidad;
        switch (opcionSel){
            case 1 -> especialidad = Especialidad.PASTELERIA;
            case 2 -> especialidad = Especialidad.PASTELERIA;
            case 3 -> especialidad = Especialidad.NINGUNA;
            default -> especialidad = Especialidad.NINGUNA;
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

        System.out.println("Ingrese el ID del Chef");
        String idChef = sc.nextLine();
        sc.nextLine();
        Chef chef = this.getChef(UUID.fromString(idChef));
        if(chef!=null){
            System.out.println("Ingrese el ID de Evento");
            String idEvento = sc.nextLine();
            sc.nextLine();

            Evento evento = this.getEventoService().getEvento(UUID.fromString(idEvento));
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
