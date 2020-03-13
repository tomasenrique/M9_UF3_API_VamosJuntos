package VJAPI.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservas;

    @ManyToOne(targetEntity = Evento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento_reserva") // campo o columna a crear en la tabla
    private Evento id_evento;

    @ManyToOne(targetEntity = Coche.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coche_reserva") // campo o columna a crear en la tabla
    private Coche id_coche;

    // Builders
    public Reserva() {
    }

    public Reserva(Evento id_evento, Coche id_coche) {
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

}
