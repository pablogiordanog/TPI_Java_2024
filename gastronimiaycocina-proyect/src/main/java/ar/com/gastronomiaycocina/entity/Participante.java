package ar.com.gastronomiaycocina.entity;

import ar.com.gastronomiaycocina.enumeration.InteresCulinarioEnum;

import java.util.Objects;
import java.util.UUID;

public class Participante {
    private UUID id;
    private String nombreYApellido;
    private InteresCulinarioEnum interesCulinario;

    public Participante(UUID id, String nombreYApellido, InteresCulinarioEnum interesCulinario){
        this.setId(id);
        this.setNombreYApellido(nombreYApellido);
        this.setInteresCulinario(interesCulinario);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Participante Id  :").append(this.getId()).append('\n')
                .append("Nombre y Apellido:").append(this.getNombreYApellido()).append('\n')
                .append("Interes Culinario:").append(this.getInteresCulinario()).append('\n')
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
        Participante participante = (Participante) obj;
        return Objects.equals(this.getId(), participante.getId());
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

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public InteresCulinarioEnum getInteresCulinario() {
        return interesCulinario;
    }

    public void setInteresCulinario(InteresCulinarioEnum interesCulinario) {
        this.interesCulinario = interesCulinario;
    }
}
