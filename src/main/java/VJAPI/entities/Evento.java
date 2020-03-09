package VJAPI.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;
    protected String nombre_evento;
    private String recinto;
    private String ciudad;
    private LocalDate fecha; // Solo fecha yyyy-MM-dd
    private LocalTime hora; // Solo hora HH:mm:ss
    private String path_imagen; // ubicacion de una imagen en el servidor
    private String info_complementaria_evento;

    // Builders
    public Evento() {
    }

    public Evento(String nombre_evento, String recinto, String ciudad, LocalDate fecha, LocalTime hora, String path_imagen, String info_complementaria_evento) {
        this.nombre_evento = nombre_evento;
        this.recinto = recinto;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.hora = hora;
        this.path_imagen = path_imagen;
        this.info_complementaria_evento = info_complementaria_evento;
    }

    // Setter and Getter
    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public String getRecinto() {
        return recinto;
    }

    public void setRecinto(String recinto) {
        this.recinto = recinto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPath_imagen() {
        return path_imagen;
    }

    public void setPath_imagen(String path_imagen) {
        this.path_imagen = path_imagen;
    }

    public String getInfo_complementaria_evento() {
        return info_complementaria_evento;
    }

    public void setInfo_complementaria_evento(String info_complementaria_evento) {
        this.info_complementaria_evento = info_complementaria_evento;
    }
}
