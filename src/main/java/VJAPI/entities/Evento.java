package VJAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    @Size(max = 20)
    @Column(unique = true)
    private String referencia; // sera la referencia unica para poder ubicar el evento asociado al coche.

    private String nombre_evento; // lugar o accion a realizar con el coche
    private String recinto; // ubicacion donde va el coche
    private String ciudad; // lugar donde se obtiene el coche
    private LocalDate fecha; // Solo fecha yyyy-MM-dd, sera el dia en que se realiza el evento, ingresado de forma manual
    private LocalTime hora; // Solo hora HH:mm:ss, sera la en que se realiza el evento, ingresado de forma manual
    private String path_imagen; // ubicacion de una imagen en el servidor
    private String info_complementaria_evento;

    @OneToMany(mappedBy = "id_evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Reserva.class)
    private List<Reserva> listaEventoReserva = new ArrayList<>();

    // Builders
    public Evento() {
    }

    public Evento(@Size(max = 20) String referencia, String nombre_evento, String recinto, String ciudad, LocalDate fecha, LocalTime hora, String path_imagen, String info_complementaria_evento) {
        this.referencia = referencia;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia_evento) {
        this.referencia = referencia_evento;
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
