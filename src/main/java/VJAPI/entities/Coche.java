package VJAPI.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Coche implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_coche;
    private String telf_propietario;
    private String tipo_vehiculo;
    private String modelo;
    private String anyo;
    private String punto_salida; // punto de salida (ciudad donde sale el coche)
    private int num_plazas_libres;
    private int num_plazas_ocupadas;
    private String info_complementaria_coche;

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario") // campo o columna a crear en la tabla
    private Usuario id_usuario;

    @OneToMany(mappedBy = "id_coche", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Reserva.class)
    private List<Reserva> listaCocheReserva = new ArrayList<>();

    // Builders
    public Coche() {
    }

    public Coche(String telf_propietario, String tipo_vehiculo, String modelo, String anyo, String punto_salida, int num_plazas_libres, int num_plazas_ocupadas, String info_complementaria_coche, Usuario id_usuario) {
        this.telf_propietario = telf_propietario;
        this.tipo_vehiculo = tipo_vehiculo;
        this.modelo = modelo;
        this.anyo = anyo;
        this.punto_salida = punto_salida;
        this.num_plazas_libres = num_plazas_libres;
        this.num_plazas_ocupadas = num_plazas_ocupadas;
        this.info_complementaria_coche = info_complementaria_coche;
        this.id_usuario = id_usuario;
    }

    // Setter and Getter
    public Long getId_coche() {
        return id_coche;
    }

    public void setId_coche(Long id_coche) {
        this.id_coche = id_coche;
    }

    public String getTelf_propietario() {
        return telf_propietario;
    }

    public void setTelf_propietario(String telf_propietario) {
        this.telf_propietario = telf_propietario;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getPunto_salida() {
        return punto_salida;
    }

    public void setPunto_salida(String punto_salida) {
        this.punto_salida = punto_salida;
    }

    public int getNum_plazas_libres() {
        return num_plazas_libres;
    }

    public void setNum_plazas_libres(int num_plazas_libres) {
        this.num_plazas_libres = num_plazas_libres;
    }

    public int getNum_plazas_ocupadas() {
        return num_plazas_ocupadas;
    }

    public void setNum_plazas_ocupadas(int num_plazas_ocupadas) {
        this.num_plazas_ocupadas = num_plazas_ocupadas;
    }

    public String getInfo_complementaria_coche() {
        return info_complementaria_coche;
    }

    public void setInfo_complementaria_coche(String info_complementaria_coche) {
        this.info_complementaria_coche = info_complementaria_coche;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public List<Reserva> getListaCocheReserva() {
        return listaCocheReserva;
    }

    public void setListaCocheReserva(List<Reserva> listaCocheReserva) {
        this.listaCocheReserva = listaCocheReserva;
    }
}
