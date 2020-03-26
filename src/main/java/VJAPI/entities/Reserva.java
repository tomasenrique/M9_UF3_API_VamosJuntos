package VJAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservas;

    @Size(max = 20)
    @Column(unique = true)
    private String referencia; // Sera la fererencia unica para poder ubicar una reserva

    private LocalDate fecha; // Solo fecha yyyy-MM-dd, sera el dia que se hizo la reserva
    private LocalTime hora; // Solo hora HH:mm:ss, sera la hora que se hizo la reserva

    // OJO al poner  CascadeType.DETACH indicamos que solo se borre la reserva y no asociado a ella.
    @ManyToOne(targetEntity = Evento.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_evento_reserva") // campo o columna a crear en la tabla
    private Evento id_evento;

    @ManyToOne(targetEntity = Coche.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_coche_reserva") // campo o columna a crear en la tabla
    private Coche id_coche;

    // Builders
    public Reserva() {
    }

    public Reserva(@Size(max = 20) String referencia, LocalDate fecha, LocalTime hora, Evento id_evento, Coche id_coche) {
        this.referencia = referencia;
        this.fecha = fecha;
        this.hora = hora;
        this.id_evento = id_evento;
        this.id_coche = id_coche;
    }


    // Setter ang Getter
    public Long getId_reservas() {
        return id_reservas;
    }

    public void setId_reservas(Long id_reservas) {
        this.id_reservas = id_reservas;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia_reserva) {
        this.referencia = referencia_reserva;
    }

    public Evento getId_evento() {
        return id_evento;
    }

    public void setId_evento(Evento id_evento) {
        this.id_evento = id_evento;
    }

    public Coche getId_coche() {
        return id_coche;
    }

    public void setId_coche(Coche id_coche) {
        this.id_coche = id_coche;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaReserva) {
        this.fecha = fechaReserva;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime horaReserva) {
        this.hora = horaReserva;
    }
}
