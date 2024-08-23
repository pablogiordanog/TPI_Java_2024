package ar.com.gastronomiaycocina.entity;

import java.util.UUID;

public class Resena {
    private UUID id;
    private Evento evento;
    private Participante participante;
    private Integer calificiacion;
    private String comentario;

    public Resena(UUID id, Evento evento, Participante participante, Integer calificiacion, String comentario){
        this.setId(id);
        this.setEvento(evento);
        this.setParticipante(participante);
        this.setCalificiacion(calificiacion);
        this.setComentario(comentario);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reseña Id:").append(this.getId()).append('\n')
                .append("Evento      :").append(this.getEvento().getNombre()).append('\n')
                .append("Participante:").append(this.getParticipante().getNombreYApellido()).append('\n')
                .append("Calificación:").append(this.getCalificiacion()).append('\n')
                .append("Comentario  :").append(this.getComentario()).append('\n')
                .append("=====================================================").append('\n');

        return stringBuilder.toString();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Integer getCalificiacion() {
        return calificiacion;
    }

    public void setCalificiacion(Integer calificiacion) {
        this.calificiacion = calificiacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
