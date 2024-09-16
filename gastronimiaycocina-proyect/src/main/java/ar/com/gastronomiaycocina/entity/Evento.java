package ar.com.gastronomiaycocina.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Evento {
    private UUID id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private Integer capacidad;
    private Chef chef;
    private Map<UUID, Participante> participantes;

    public Evento(UUID id, String nombre, String descripcion, LocalDateTime fechaHora,
                  String ubicacion, Integer capacidad, Map participantes){
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setFechaHora(fechaHora);
        this.setUbicacion(ubicacion);
        this.setCapacidad(capacidad);
        this.setParticipantes(participantes);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Evento Id         :").append(this.getId()).append('\n')
                .append("Nombre            :").append(this.getNombre()).append('\n')
                .append("Descripción       :").append(this.getDescripcion()).append('\n')
                .append("Capacidad         :").append(this.getCapacidad()).append('\n')
                .append("Fecha             :").append(this.getFechaHora().getDayOfMonth() + "/" + this.getFechaHora().getMonthValue() + "/" + this.getFechaHora().getYear()).append('\n')
                .append("Hora              :").append(this.getFechaHora().getHour() + ":" + this.getFechaHora().getMinute()).append('\n')
                .append("Capacidad Máxima  :").append(this.getCapacidad()).append('\n')
                .append("Capacidad Restante:").append(this.getCapacidad() - this.getParticipantes().size()).append('\n')
                .append("=====================================================").append('\n');

        return stringBuilder.toString();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Map<UUID, Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Map<UUID, Participante> participantes) {
        this.participantes = participantes;
    }

    public boolean esCapacidadMaxima(){
        int totalParcipantes = this.getParticipantes().size() + 1;
        return totalParcipantes>this.getCapacidad();
    }

    public String[] getDato(){
        String fecha  = this.getFechaHora().getDayOfMonth() + "/" + this.getFechaHora().getMonthValue() + "/" + this.getFechaHora().getYear();
        String hora   = this.getFechaHora().getHour() + ":" + this.getFechaHora().getMinute();
        String[] dato = {this.getId().toString(),
                        this.getNombre(),
                        this.getDescripcion(),
                        this.getCapacidad().toString(),
                        fecha,
                        hora,
                        String.valueOf(this.getParticipantes().size())};
        return dato;
    }
}
