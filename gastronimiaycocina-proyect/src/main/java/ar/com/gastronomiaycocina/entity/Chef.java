package ar.com.gastronomiaycocina.entity;

import ar.com.gastronomiaycocina.enumeration.EspecialidadEnum;

import java.util.Objects;
import java.util.UUID;

public class Chef {
    private UUID id;
    private String nombre;
    private EspecialidadEnum especialidad;

    public Chef(UUID id, String nombre, EspecialidadEnum especialidad){
        this.setId(id);
        this.setNombre(nombre);
        this.setEspecialidad(especialidad);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Chef Id     :").append(this.getId()).append('\n')
                .append("Nombre      :").append(this.getNombre()).append('\n')
                .append("Especialidad:").append(this.getEspecialidad()).append('\n')
                .append("=====================================================").append('\n');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Chef chef = (Chef) obj;
        return Objects.equals(this.getId(), chef.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
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

    public EspecialidadEnum getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEnum especialidad) {
        this.especialidad = especialidad;
    }
}
